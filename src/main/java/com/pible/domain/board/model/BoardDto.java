package com.pible.domain.board.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import processor.SnakeSetter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@SnakeSetter
public class BoardDto {
    @NotNull
    private Long channelId;
    private String userNickName;
    @NotNull
    private Long boardCategoryId;
    @NotEmpty
    private String anonymousYn;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    private String adultYn;
    private List<String> tagList;

}
