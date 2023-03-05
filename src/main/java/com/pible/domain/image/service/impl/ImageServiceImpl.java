package com.pible.domain.image.service.impl;

import com.pible.common.entity.BoardEntity;
import com.pible.common.entity.FanartEntity;
import com.pible.common.entity.ImageEntity;
import com.pible.common.enums.ResponseCode;
import com.pible.common.exception.BusinessException;
import com.pible.domain.image.dao.ImageRepository;
import com.pible.domain.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
    public void uploadImageFiles(List<MultipartFile> multipartFileList, BoardEntity boardEntity, FanartEntity fanartEntity) {
        if(CollectionUtils.isEmpty(multipartFileList)) {
            return;
        }

        List<ImageEntity> imageEntityList = new ArrayList<>();

        for(MultipartFile file : multipartFileList) {
            String fileName = generateFile(file);

            imageEntityList.add(
                    ImageEntity.builder()
                            .imageName(fileName)
                            .imagePath(this.imagePath)
                            .oriImageName(file.getOriginalFilename())
                            .boardEntity(boardEntity)
                            .fanartEntity(fanartEntity)
                            .build()
            );
        }

        imageRepository.saveAll(imageEntityList);
    }

    @Override
    @Transactional
    public void uploadThumbnail(MultipartFile multipartFile, FanartEntity fanartEntity) {
        if(multipartFile == null) {
            return;
        }

        String fileName = generateFile(multipartFile);

        imageRepository.save(
                ImageEntity.builder()
                .imageName(fileName)
                .imagePath(this.imagePath)
                .oriImageName(multipartFile.getOriginalFilename())
                .fanartEntity(fanartEntity)
                .build()
        );
    }

    private String generateFile(MultipartFile file) {
        String fileName = UUID.randomUUID().toString();

        Path copyOfLocation = Paths.get(this.imagePath + File.separator + StringUtils.cleanPath(fileName));
        try {
            Files.copy(file.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new BusinessException(ResponseCode.FAIL);
        }

        return fileName;
    }

}
