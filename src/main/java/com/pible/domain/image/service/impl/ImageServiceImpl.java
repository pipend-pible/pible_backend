package com.pible.domain.image.service.impl;

import com.pible.common.entity.BoardEntity;
import com.pible.common.entity.FanartEntity;
import com.pible.common.entity.ImageEntity;
import com.pible.common.enums.ResponseCode;
import com.pible.common.exception.BusinessException;
import com.pible.domain.board.dao.BoardRepository;
import com.pible.domain.fanart.dao.FanartRepository;
import com.pible.domain.image.dao.ImageRepository;
import com.pible.domain.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    @Value("${image.path}")
    private String imagePath;

    @Override
    @Transactional
    public void uploadImageFiles(MultipartHttpServletRequest request, BoardEntity boardEntity, FanartEntity fanartEntity) {
        List<MultipartFile> fileList = request.getFiles("images");
        List<ImageEntity> imageEntityList = new ArrayList<>();

        for(MultipartFile file : fileList) {
            String fileName = UUID.randomUUID().toString();

            imageEntityList.add(
                    ImageEntity.builder()
                            .imageName(fileName)
                            .imagePath(this.imagePath)
                            .oriImageName(file.getOriginalFilename())
                            .boardEntity(boardEntity)
                            .fanartEntity(fanartEntity)
                            .build()
            );

            Path copyOfLocation = Paths.get(this.imagePath + File.separator + StringUtils.cleanPath(fileName));
            try {
                Files.copy(file.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new BusinessException(ResponseCode.FAIL);
            }
        }

        imageRepository.saveAll(imageEntityList);
    }

    @Override
    @Transactional
    public void uploadThumbnail(MultipartHttpServletRequest request, FanartEntity fanartEntity) {
        MultipartFile file = request.getFile("thumbnail");

        if(file == null) {
            return;
        }

        String fileName = UUID.randomUUID().toString();

        Path copyOfLocation = Paths.get(this.imagePath + File.separator + StringUtils.cleanPath(fileName));
        try {
            Files.copy(file.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new BusinessException(ResponseCode.FAIL);
        }

        imageRepository.save(
                ImageEntity.builder()
                .imageName(fileName)
                .imagePath(this.imagePath)
                .oriImageName(file.getOriginalFilename())
                .fanartEntity(fanartEntity)
                .build()
        );
    }

}
