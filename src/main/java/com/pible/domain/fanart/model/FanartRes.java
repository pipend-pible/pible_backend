package com.pible.domain.fanart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FanartRes extends FanartDto {
    @NotNull
    private Long fanartId;
    private List<String> imageUrlList;
    private LocalDateTime createDate;
}
