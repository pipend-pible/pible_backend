package com.pible.domain.channel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import processor.SnakeSetter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@SnakeSetter
@NoArgsConstructor
@AllArgsConstructor
public class ContentDto {
    @NotNull
    private Long channelId;
    private Long userId;
    private Long boardCategoryId;
    private String boardAnonymous;
    private String title;
    private String content;
    private List<String> tagList;
    private String adult;
}
