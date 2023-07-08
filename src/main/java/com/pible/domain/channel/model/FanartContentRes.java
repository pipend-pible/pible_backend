package com.pible.domain.channel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FanartContentRes extends ContentRes {
    private Long fanartId;
    private String fanartThumbnailImageUrl;

    public FanartContentRes(Long channelId, String category, String title,
                            String userEmail, String userNickName, Integer likeCount,
                            Integer hitCount, String delimitedTagList, LocalDateTime createDate, Long boardId,
                            String fanartThumbnailImageUrl) {
        super(channelId, category, title, userEmail, userNickName, likeCount, hitCount, delimitedTagList, createDate);
        this.fanartId = boardId;
        this.fanartThumbnailImageUrl = fanartThumbnailImageUrl;
    }
}
