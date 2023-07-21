package com.bftcom.mediastorage.web.controller;

import com.bftcom.mediastorage.web.controller.parameters.PagingParameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/media")
public class MediaController {

    @GetMapping()
    private String Get(@RequestParam PagingParameters parameters) {
        return "Hello, world!";
    }
}
