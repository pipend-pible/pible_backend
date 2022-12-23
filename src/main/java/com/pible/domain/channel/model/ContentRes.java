package com.pible.domain.channel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContentRes {
    private Long channelId;
    private Long boardId;
    private Long fanartId;
    private String category;
    private String title;
    private Long userId;
    private String userNickName;
    private Integer likeCount;
    private Integer hitCount;

}
