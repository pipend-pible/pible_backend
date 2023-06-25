package com.pible.domain.comment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long userId;
    private Long fanartId;
    private Long boardId;
    @NotEmpty
    private String content;
    private String anonymousYn;

}
