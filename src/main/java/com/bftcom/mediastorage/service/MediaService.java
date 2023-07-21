package com.bftcom.mediastorage.service;

import com.bftcom.mediastorage.data.entity.Media;
import com.bftcom.mediastorage.data.repository.MediaRepository;
import com.bftcom.mediastorage.data.repository.jdbc.JdbcMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService {

    private MediaRepository mediaRepository;

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
