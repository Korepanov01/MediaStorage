package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.model.entity.Media;
import com.bftcom.mediastorage.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService {

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

}
