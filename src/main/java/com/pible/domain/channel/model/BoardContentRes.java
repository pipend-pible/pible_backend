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
public class BoardContentRes extends ContentRes {
    private Long boardId;

    public BoardContentRes(Long channelId, String category, String title,
                           String userEmail, String userNickName, Integer likeCount,
                           Integer hitCount, String delimitedTagList, LocalDateTime createDate, Long boardId) {
        super(channelId, category, title, userEmail, userNickName, likeCount, hitCount, delimitedTagList, createDate);
        this.boardId = boardId;
    }
}
