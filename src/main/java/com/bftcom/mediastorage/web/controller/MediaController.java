package com.bftcom.mediastorage.web.controller;

import com.bftcom.mediastorage.service.MediaService;
import com.bftcom.mediastorage.web.controller.parameters.MediaSearchParameters;
import com.bftcom.mediastorage.web.controller.parameters.PagingParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/media")
public class MediaController {

    private final MediaService mediaService;

    @Autowired
    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping
    private String Get(
            @RequestParam(required = false)
            MediaSearchParameters parameters) {
        return "Hello, world!";
    }
}
