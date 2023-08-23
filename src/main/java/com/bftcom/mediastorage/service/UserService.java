package com.bftcom.mediastorage.service;

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
    public void addRole(@NonNull Long userId, @NonNull Long roleId)
            throws EntityNotFoundException, EntityExistsException {
        addOrDeleteRole(userId, roleId, false);
    }

    @Transactional
    public void deleteRole(@NonNull Long userId, @NonNull Long roleId)
            throws EntityNotFoundException {
        addOrDeleteRole(userId, roleId, true);
    }

    private void addOrDeleteRole(@NonNull Long userId, @NonNull Long roleId, boolean isDelete)
            throws EntityNotFoundException {
        User user = userRepository.findById(userId);

        if (user == null)
            throw new EntityNotFoundException("Пользователь не найден");

        Role role = roleRepository.findById(roleId);

        if (role == null)
            throw new EntityNotFoundException("Роль не найдена");

        if (isDelete)
            user.removeRole(role);
        else
            user.addRole(role);

        userRepository.update(user);
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
