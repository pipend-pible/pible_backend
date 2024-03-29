package com.pible.domain.comment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommentRes extends CommentDto {
    private Long commentId;
    private String nickName;
    private Integer likeCount;
    private Integer claimCount;
    private LocalDateTime createDate;
}
