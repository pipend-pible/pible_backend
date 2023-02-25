package com.pible.domain.image.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
    private String repImageYn;
    private Long fanartId;
    private Long boardId;
}
