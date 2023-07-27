package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.model.entity.UserRole;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.repository.ParametersSearchRepository;
import com.bftcom.mediastorage.repository.RoleRepository;
import com.bftcom.mediastorage.repository.UserRepository;
import com.bftcom.mediastorage.repository.UserRoleRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService extends BaseService<User, SearchStringParameters> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    public void addRoles(@NonNull Long userId, @NonNull List<Long> rolesIds)
            throws EntityNotFoundException {
        if (!userRepository.isExists(userId))
            throw new EntityNotFoundException("Пользователь с id(" + userId + ") не найден");

        for (Long roleId : rolesIds) {
            if (!roleRepository.isExists(roleId))
                throw new EntityNotFoundException("Роль с id(" + roleId + ") не найдена");
        }

        for (Long roleId : rolesIds) {
            if (!roleRepository.isExists(roleId))
                throw new EntityNotFoundException("Роль с id(" + roleId + ") не найдена");
        }

        List<UserRole> userRoleList = rolesIds
                .stream()
                .map(roleId -> new UserRole(roleId, userId))
                .collect(Collectors.toList());

        userRoleRepository.saveAll(userRoleList);
    }

    @Override
    protected ParametersSearchRepository<User, SearchStringParameters> getMainRepository() {
        return userRepository;
    }

    @Override
    protected boolean isSameEntityExists(@NonNull User user) {
        return userRepository.findByEmail(user.getEmail()).isPresent()
                || userRepository.findByName(user.getName()).isPresent();
    }
}
