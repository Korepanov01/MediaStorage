package com.bftcom.mediastorage.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MediaController {

    @GetMapping("/")
    private String Get() {
        return "Hello, world!";
    }
}
