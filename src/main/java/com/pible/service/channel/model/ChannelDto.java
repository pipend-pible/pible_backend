package com.pible.service.channel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelDto {
    @NotEmpty
    private String category;
    @NotEmpty
    private String channelName;
    @NotEmpty
    private String owner;
    @NotEmpty
    private String ownerInfo;

    // TODO : 공통코드 테이블 구조로 고민
    @NotEmpty
    private String ageLimit;
    private String apply;
}
