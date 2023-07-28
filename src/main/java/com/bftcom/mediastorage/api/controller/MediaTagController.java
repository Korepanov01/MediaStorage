package com.bftcom.mediastorage.api.controller;

import com.bftcom.mediastorage.model.entity.MediaTag;
import com.bftcom.mediastorage.model.request.mediatag.PostMediaTag;
import com.bftcom.mediastorage.service.MediaTagService;
import com.bftcom.mediastorage.service.SaveDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MediaTagController extends SaveDeleteController<MediaTag, PostMediaTag> {

    private final MediaTagService mediaTagService;

    @Override
    protected SaveDeleteService<MediaTag> getMainService() {
        return mediaTagService;
    }
}
