package com.pible.domain.channel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ContentDto {
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
    private List<String> tagList;
    @NotEmpty
    private String adult;

}
