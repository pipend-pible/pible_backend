package com.pible.domain.image.service;

import com.pible.common.entity.BoardEntity;
import com.pible.common.entity.FanartEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface ImageService {
    void uploadImageFiles(MultipartHttpServletRequest request, BoardEntity boardEntity, FanartEntity fanartEntity);
    void uploadThumbnail(MultipartHttpServletRequest request, FanartEntity fanartEntity);

}
