package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.entity.Media;
import com.bftcom.mediastorage.model.parameters.MediaSearchParameters;
import com.bftcom.mediastorage.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService implements IService<Media, MediaSearchParameters>{

    private final MediaRepository mediaRepository;

    @Autowired
    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public List<Media> getRandom(int limit) {
        if (limit < 1 || limit > 100)
            throw new IllegalArgumentException("Limit must be between 1 and 100");

        return mediaRepository.findRandom(limit);
    }

    public List<Media> findByParameters(MediaSearchParameters parameters) {
        return mediaRepository.findByParameters(parameters);
    }
    @Override
    public Media save(Media t) throws EntityAlreadyExistsException {
        return null;
    }

    @Override
    public void delete(Media t) throws EntityNotFoundException {

    }
}
