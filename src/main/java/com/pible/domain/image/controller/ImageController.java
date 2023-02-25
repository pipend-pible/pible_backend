package com.pible.domain.image.controller;

import com.pible.domain.image.model.ImageDto;
import com.pible.domain.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;

    @PostMapping("")
    public void uploadImageFile(MultipartHttpServletRequest request, @RequestBody ImageDto imageDto) {
        imageService.uploadImageFile(request, imageDto);
    }
}
