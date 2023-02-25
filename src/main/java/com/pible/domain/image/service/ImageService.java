package com.pible.domain.image.service;

import com.pible.domain.image.model.ImageDto;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface ImageService {
    void uploadImageFile(MultipartHttpServletRequest request, ImageDto imageDto);
}
