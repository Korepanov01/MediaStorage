package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.DbDataException;
import com.bftcom.mediastorage.exception.IllegalOperationException;
import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.model.searchparameters.SearchStringParameters;
import com.bftcom.mediastorage.repository.ParametersSearchRepository;
import com.bftcom.mediastorage.repository.RoleRepository;
import com.bftcom.mediastorage.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class UserService extends ParameterSearchService<User, SearchStringParameters> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Transactional
    public void register(@NonNull User user) throws EntityExistsException {
        if (userRepository.existsByName(user.getName())) {
            throw new EntityExistsException("Имя пользователя уже занято!");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EntityExistsException("Пользователь не найден");
        }
        userRepository.save(user);
    }

    @Transactional
    public void updateName(@NonNull String name, @NonNull Long id) throws EntityExistsException {
        if (userRepository.existsByName(name)) {
            throw new EntityExistsException("Имя пользователя уже занято!");
        }

        User user = userRepository.findById(id);

        if (user == null)
            throw new EntityNotFoundException("Пользователь не найден");

        user.setName(name);

        userRepository.update(user);
    }

    @Transactional
    public void updateEmail(@NonNull String email, @NonNull Long id) throws EntityExistsException {
        if (userRepository.existsByEmail(email)) {
            throw new EntityExistsException("Почта уже используется");
        }

        User user = userRepository.findById(id);

        if (user == null)
            throw new EntityNotFoundException("Пользователь не найден");

        user.setEmail(email);

        userRepository.update(user);
    }

    @Transactional
    public void addAdminRole(@NonNull Long userId)
            throws EntityNotFoundException, EntityExistsException {
        addOrRemoveAdminRole(userId,false);
    }

    @Transactional
    public void removeAdminRole(@NonNull Long userId)
            throws EntityNotFoundException, EntityExistsException {
        addOrRemoveAdminRole(userId,true);
    }

    private void addOrRemoveAdminRole(@NonNull Long userId, boolean isRemove)
            throws EntityNotFoundException, EntityExistsException {
        Role adminRole = roleRepository.findByName(Role.ADMIN);
        if (adminRole == null)
            throw new DbDataException("в БД отсутствует роль " + Role.ADMIN);

        User user = userRepository.findById(userId);
        if (user == null)
            throw new EntityNotFoundException("Пользователь не найден");

        if (isRemove)
            user.removeRole(adminRole);
        else
            user.addRole(adminRole);

        userRepository.update(user);
    }

    @Override
    @Transactional
    public void delete(@NonNull Long id) throws EntityNotFoundException, IllegalOperationException {
        User user = getMainRepository().findById(id);
        if (user == null)
            throw new EntityNotFoundException("Пользователь не найден");

        if (user.getRoles().stream().anyMatch(role -> role.getName().equals(Role.SUPER_ADMIN)))
            throw new IllegalOperationException("Нельзя удалить суперадмина");

        getMainRepository().delete(user);
    }

    @Override
    protected ParametersSearchRepository<User, SearchStringParameters> getMainRepository() {
        return userRepository;
    }

    @Override
    public boolean isSameEntityExists(@NonNull User user) {
        return userRepository.existsByEmail(user.getEmail()) || userRepository.existsByName(user.getName());
    }
}
