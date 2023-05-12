package com.pible.common.utils;

import com.cloudinary.Cloudinary;
import com.pible.common.property.CloudinaryProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class CloudinaryUtils {
    private static Cloudinary cloudinary;
    private final CloudinaryProperties cloudinaryProperties;

    @PostConstruct
    public void postConstruct() {
        Map<String, String> cloudinaryConfigMap = new HashMap<>();
        cloudinaryConfigMap.put("cloud_name", cloudinaryProperties.getName());
        cloudinaryConfigMap.put("api_key", cloudinaryProperties.getKey());
        cloudinaryConfigMap.put("api_secret", cloudinaryProperties.getSecretKey());

        cloudinary = new Cloudinary(cloudinaryConfigMap);
    }

    public static Cloudinary getCloudinary() {
        return cloudinary;
    }
}
