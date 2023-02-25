package com.pible.domain.image.service.impl;

import com.pible.common.entity.BoardEntity;
import com.pible.common.entity.FanartEntity;
import com.pible.common.entity.ImageEntity;
import com.pible.common.enums.ResponseCode;
import com.pible.common.exception.BusinessException;
import com.pible.domain.board.dao.BoardRepository;
import com.pible.domain.fanart.dao.FanartRepository;
import com.pible.domain.image.dao.ImageRepository;
import com.pible.domain.image.model.ImageDto;
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
    private final BoardRepository boardRepository;
    private final FanartRepository fanartRepository;
    @Value("${image.path}")
    private String imagePath;

    @Override
    @Transactional
    public void uploadImageFile(MultipartHttpServletRequest request, ImageDto imageDto) {
        List<MultipartFile> fileList = request.getFiles("fileName");
        List<ImageEntity> imageEntityList = new ArrayList<>();

        BoardEntity boardEntity = null;
        FanartEntity fanartEntity = null;

        if(imageDto.getFanartId() != null && imageDto.getBoardId() != null) {
            throw new BusinessException(ResponseCode.FAIL);
        } else if (imageDto.getBoardId() != null) {
            boardEntity = boardRepository.findById(imageDto.getBoardId())
                    .orElseThrow(() -> new BusinessException(ResponseCode.NO_DATA));
        } else if (imageDto.getFanartId() != null) {
            fanartEntity = fanartRepository.findById(imageDto.getFanartId())
                    .orElseThrow(() -> new BusinessException(ResponseCode.NO_DATA));
        } else {
            throw new BusinessException(ResponseCode.FAIL);
        }

        for(MultipartFile file : fileList) {
            String fileName = UUID.randomUUID().toString();

            imageEntityList.add(
                    ImageEntity.builder()
                            .repImageYn(imageDto.getRepImageYn())
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

}
