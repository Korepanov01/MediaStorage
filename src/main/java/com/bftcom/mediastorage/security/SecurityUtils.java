package com.bftcom.mediastorage.security;

import com.bftcom.mediastorage.model.entity.Media;
import com.bftcom.mediastorage.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("securityUtils")
@RequiredArgsConstructor
public class SecurityUtils {

    private final MediaService mediaService;

    public boolean checkUserId(Authentication authentication, Long id) {
        Optional<Long> optionalAuthUserId = AuthParser.getUserId(authentication);
        return optionalAuthUserId.map(authUserId -> authUserId.equals(id)).orElse(false);
    }

    public boolean checkUserOwning(Authentication authentication, Long mediaId) {
        Optional<Long> optionalAuthUserId = AuthParser.getUserId(authentication);
        if (optionalAuthUserId.isEmpty())
            return false;

        Optional<Media> optionalMedia = mediaService.findById(mediaId);

        return optionalMedia.map(media -> media.getUserId().equals(optionalAuthUserId.get())).orElse(false);
    }
}
