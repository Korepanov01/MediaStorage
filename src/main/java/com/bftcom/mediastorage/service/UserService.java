package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.DbDataException;
import com.bftcom.mediastorage.exception.EntityExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.exception.IllegalOperationException;
import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.model.searchparameters.SearchStringParameters;
import com.bftcom.mediastorage.repository.CustomJpaRepository;
import com.bftcom.mediastorage.repository.RoleRepository;
import com.bftcom.mediastorage.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService extends ParameterSearchService<User, SearchStringParameters> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(@NonNull String name, @NonNull String email, @NonNull String password) throws EntityExistsException {
        if (userRepository.existsByName(name)) {
            throw new EntityExistsException("Имя пользователя уже занято");
        }
        if (userRepository.existsByEmail(email)) {
            throw new EntityExistsException("Почта уже занята");
        }

        User user = new User(name, passwordEncoder.encode(password), email);

        userRepository.save(user);

        return user;
    }

    public void updateName(@NonNull String name, @NonNull Long id) throws EntityExistsException, EntityNotFoundException {
        if (userRepository.existsByName(name)) {
            throw new EntityExistsException("Имя пользователя уже занято!");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        user.setName(name);
    }

    public void updateEmail(@NonNull String email, @NonNull Long id) throws EntityExistsException, EntityNotFoundException {
        if (userRepository.existsByEmail(email)) {
            throw new EntityExistsException("Почта уже используется");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        user.setEmail(email);
    }

    public void addAdminRole(@NonNull Long userId)
            throws EntityNotFoundException, EntityExistsException {
        addOrRemoveAdminRole(userId,false);
    }

    public void removeAdminRole(@NonNull Long userId)
            throws EntityNotFoundException, EntityExistsException {
        addOrRemoveAdminRole(userId,true);
    }

    private void addOrRemoveAdminRole(@NonNull Long userId, boolean isRemove)
            throws EntityNotFoundException {
        Role adminRole = roleRepository.findByName(Role.ADMIN)
                .orElseThrow(() -> new DbDataException("в БД отсутствует роль " + Role.ADMIN));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        if (isRemove)
            user.removeRole(adminRole);
        else
            user.addRole(adminRole);

        userRepository.save(user);
    }

    @Override
    public List<User> findByParameters(SearchStringParameters parameters) throws EntityNotFoundException {
        return userRepository.findByParameters(
                parameters.getSearchString(),
                PageRequest.of(parameters.getPageIndex(), parameters.getPageSize())
        );
    }

    @Override
    public void delete(@NonNull Long id) throws EntityNotFoundException, IllegalOperationException {
        User user = getMainRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        if (user.getRoles().stream().anyMatch(role -> role.getName().equals(Role.SUPER_ADMIN)))
            throw new IllegalOperationException("Нельзя удалить суперадмина");

        getMainRepository().delete(user);
    }

    @Override
    protected CustomJpaRepository<User> getMainRepository() {
        return userRepository;
    }

    @Override
    public boolean isSameEntityExists(@NonNull User user) {
        return userRepository.existsByEmail(user.getEmail()) || userRepository.existsByName(user.getName());
    }
}
