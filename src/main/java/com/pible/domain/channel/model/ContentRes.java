package com.pible.domain.channel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ContentRes {
    private Long channelId;
    private String category;
    private String title;
    private Long userId;
    private String userEmail;
    private String userNickName;
    private Integer likeCount;
    private Integer hitCount;
    private List<String> tagList;
    private LocalDateTime createDate;

    public ContentRes(Long channelId, String category, String title, Long userId,
                      String userEmail, String userNickName, Integer likeCount, Integer hitCount, String delimitedTagList,
                      LocalDateTime createDate) {
        this.channelId = channelId;
        this.category = category;
        this.title = title;
        this.userId = userId;
        this.userEmail = userEmail;
        this.userNickName = userNickName;
        this.likeCount = likeCount;
        this.hitCount = hitCount;
        this.tagList = Arrays.asList(delimitedTagList.split(","));
        this.createDate = createDate;
    }
}
