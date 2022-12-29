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

    public FanartContentRes(Long channelId, String category, String title, Long userId,
                            String userEmail, String userNickName, Integer likeCount,
                            Integer hitCount, String delimitedTagList, LocalDateTime createDate, Long boardId) {
        super(channelId, category, title, userId, userEmail, userNickName, likeCount, hitCount, delimitedTagList, createDate);
        this.fanartId = boardId;
    }
}
