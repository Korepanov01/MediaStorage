package com.bftcom.mediastorage.web.security;

import com.bftcom.mediastorage.data.entity.Media;
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
        Optional<Media> optionalMedia = mediaService.findById(mediaId);

        return optionalAuthUserId.isPresent() && optionalMedia.isPresent()
                && optionalMedia.get().getUser().getId().equals(optionalAuthUserId.get());
    }
}
