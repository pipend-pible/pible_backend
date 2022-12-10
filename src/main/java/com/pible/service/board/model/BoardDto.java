package com.pible.service.board.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    @NotNull
    private Long channelId;
    @NotNull
    private Long userId;
    @NotNull
    private Long boardCategoryId;
    @NotEmpty
    private String boardAnonymous;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    private String tag;
    @NotEmpty
    private String adult;

}
