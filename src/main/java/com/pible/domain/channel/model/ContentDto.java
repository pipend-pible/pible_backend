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
// 프론트에서 Get 메소드로 요청할 경우 화면에서 보낸 프로퍼티랑 자바 변수명이랑 맞추기 위한 어노테이션입니다.
// 내부 라이브러리로 개발되었으며 @SnakeSetter 어노테이션이 있을 경우 컴파일시 코드가 추가되어
// setUser_id 와 같은이름의 메소드가 생성됩니다.
// 프론트에서는 스네이크 케이스, 백엔드에서는 카멜 케이스를 쓰는 경우 매핑을 위해 추가되었습니다.
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
