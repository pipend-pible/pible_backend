package com.pible.domain.image.service.impl;

import com.cloudinary.utils.ObjectUtils;
import com.pible.common.Constants;
import com.pible.common.entity.BoardEntity;
import com.pible.common.entity.FanartEntity;
import com.pible.common.entity.ImageEntity;
import com.pible.common.enums.ResponseCode;
import com.pible.common.exception.BusinessException;
import com.pible.domain.image.dao.ImageRepository;
import com.pible.domain.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.pible.common.utils.CloudinaryUtils.getCloudinary;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public void uploadImageFiles(List<MultipartFile> multipartFileList, BoardEntity boardEntity, FanartEntity fanartEntity) {
        if(CollectionUtils.isEmpty(multipartFileList)) {
            return;
        }

        List<ImageEntity> imageEntityList = new ArrayList<>();

        // 반복문만큼 이미지가 업로드되기 때문에 화면단에 응답시간이 늦어질 경우 추가적인 개선 작업이 필요할 수 있습니다.
        for(MultipartFile file : multipartFileList) {
            String fileUrl = generateFile(file);

            imageEntityList.add(
                    ImageEntity.builder()
                            .imageUrl(fileUrl)
                            .oriImageName(file.getOriginalFilename())
                            .boardEntity(boardEntity)
                            .fanartEntity(fanartEntity)
                            .thumbnailYn(Constants.NO)
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

        String fileUrl = generateFile(multipartFile);

        imageRepository.save(
                ImageEntity.builder()
                .imageUrl(fileUrl)
                .oriImageName(multipartFile.getOriginalFilename())
                .fanartEntity(fanartEntity)
                .thumbnailYn(Constants.YES)
                .build()
        );
    }

    // cloudinary 에 이미지를 업로드하고 생성된 url을 가져옵니다.
    private String generateFile(MultipartFile file) {
        String fileUrl;

        try {
            fileUrl = String.valueOf(
                    getCloudinary().uploader().upload(
                            file.getBytes(),
                            ObjectUtils.emptyMap()
                    ).get("url")
            );
        } catch (IOException exception) {
            throw new BusinessException(ResponseCode.FAILED_UPLOAD_IMAGE);
        }

        return fileUrl;
    }

}
