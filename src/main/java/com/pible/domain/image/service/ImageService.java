package com.pible.domain.image.service;

import com.pible.common.entity.BoardEntity;
import com.pible.common.entity.FanartEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    void uploadImageFiles(List<MultipartFile> multipartFileList, BoardEntity boardEntity, FanartEntity fanartEntity);
    void uploadThumbnail(MultipartFile multipartFile, FanartEntity fanartEntity);

}
