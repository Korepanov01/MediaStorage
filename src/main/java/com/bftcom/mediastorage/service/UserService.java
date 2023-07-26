package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.repository.ParametersSearchRepository;
import com.bftcom.mediastorage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, SearchStringParameters> {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected ParametersSearchRepository<User, SearchStringParameters> getMainRepository() {
        return userRepository;
    }

    @Override
    protected boolean isEntityExists(User user) {
        return userRepository.findByEmail(user.getEmail()).isPresent();
    }
}
