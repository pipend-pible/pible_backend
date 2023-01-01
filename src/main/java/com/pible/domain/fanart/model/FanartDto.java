package com.pible.domain.fanart.model;

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
public class FanartDto {
    @NotNull
    private Long channelId;
    @NotNull
    private Long userId;
    @NotNull
    private Long fanartCategoryId;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    private List<String> tagList;
    @NotEmpty
    private String adultYn;
    private String imgName;

//     TODO 논의대상
//    "fanart_self": "F",

}
