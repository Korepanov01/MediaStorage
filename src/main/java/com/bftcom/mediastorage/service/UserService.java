package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.EmailAlreadyUsedException;
import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.exception.NameAlreadyUsedException;
import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.model.entity.UserRole;
import com.bftcom.mediastorage.model.searchparameters.SearchStringParameters;
import com.bftcom.mediastorage.repository.ParametersSearchRepository;
import com.bftcom.mediastorage.repository.RoleRepository;
import com.bftcom.mediastorage.repository.UserRepository;
import com.bftcom.mediastorage.repository.UserRoleRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService extends ParameterSearchService<User, SearchStringParameters> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Transactional
    public void register(@NonNull User user) throws EntityAlreadyExistsException, NameAlreadyUsedException, EmailAlreadyUsedException {
        if (userRepository.existsByName(user.getName())) {
            throw new NameAlreadyUsedException();
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyUsedException();
        }
        userRepository.save(user);

        Role role = roleRepository.findByName(Role.Names.USER.name()).orElseThrow();

        UserRole userRole = new UserRole(role.getId(), user.getId());
        userRoleRepository.save(userRole);
    }

    @Transactional
    public void updateName(@NonNull String name, @NonNull Long id) throws EntityAlreadyExistsException {
        if (userRepository.existsByName(name)) {
            throw new EntityAlreadyExistsException("Имя пользователя уже занято!");
        }

        userRepository.updateName(name, id);
    }

    @Transactional
    public void updateEmail(@NonNull String email, @NonNull Long id) throws EntityAlreadyExistsException {
        if (userRepository.existsByEmail(email)) {
            throw new EntityAlreadyExistsException("Почта уже используется!");
        }

        userRepository.updateEmail(email, id);
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
