package com.pible.channel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelRes extends ChannelDto {
    @NotNull
    private Long channelId;
}
