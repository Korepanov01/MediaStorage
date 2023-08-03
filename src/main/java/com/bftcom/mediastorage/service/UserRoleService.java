package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.entity.UserRole;
import com.bftcom.mediastorage.repository.RoleRepository;
import com.bftcom.mediastorage.repository.UserRepository;
import com.bftcom.mediastorage.repository.UserRoleRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Transactional
    public void addRole(@NonNull Long userId, @NonNull Long roleId)
            throws EntityNotFoundException, EntityAlreadyExistsException {
        if (userRepository.isExists(userId))
            throw new EntityNotFoundException("Пользователь (id: " + userId + ") не найден");

        if (roleRepository.isExists(roleId))
            throw new EntityNotFoundException("Роль (id: " + roleId + ") не найдена");

        if (userRoleRepository.isExists(userId, roleId))
            throw new EntityAlreadyExistsException("Роль (id: " + roleId + ") уже принадлежит пользователю (id: " + userId + ")");
        userRoleRepository.findByUserAndRole(userId, roleId);

        userRoleRepository.save(new UserRole(roleId, userId));
    }

    @Transactional
    public void deleteRole(@NonNull Long userId, @NonNull Long roleId)
            throws EntityNotFoundException {
        if (userRepository.isExists(userId))
            throw new EntityNotFoundException("Пользователь (id: " + userId + ") не найден");

        if (roleRepository.isExists(roleId))
            throw new EntityNotFoundException("Роль (id: " + roleId + ") не найдена");

        Optional<UserRole> optionalUserRole = userRoleRepository.findByUserAndRole(userId, roleId);

        if (optionalUserRole.isEmpty())
            throw new EntityNotFoundException("Роль (id: " + roleId + ") не принадлежит пользователю (id: " + userId + ")");

        userRoleRepository.delete(optionalUserRole.get());
    }
}