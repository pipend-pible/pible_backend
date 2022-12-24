package com.pible.domain.channel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BoardContentRes extends ContentRes {
    private Long boardId;

    public BoardContentRes(Long channelId, String category, String title, Long userId,
                           String userEmail, String userNickName, Integer likeCount,
                           Integer hitCount, String delimitedTagList, Long boardId) {
        super(channelId, category, title, userId, userEmail, userNickName, likeCount, hitCount, delimitedTagList);
        this.boardId = boardId;
    }
}
