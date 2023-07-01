package com.pible.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "channel", schema = "pible")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Where(clause = "delete_yn = 'N'")
@SequenceGenerator(allocationSize = 1, name = "channelSequence", sequenceName = "channel_sequence", schema = "pible")
public class ChannelEntity extends BaseEntity {

    @Id
    @GeneratedValue(generator = "channelSequence")
    @Column(name = "channel_id")
    private Long id;
    private String channelName;

    //TODO 채널카테고리 현재는 단순 문자열이지만 추후에 테이블로 분리할수도
    private String category;
    private String owner;

    //TODO 원작자정보 현재는 단순 문자열이지만 추후에 테이블로 분리할수도
    private String ownerInfo;
    private String ageLimit;
    private String apply;
    private int fanartTotalHitCount;
    private int boardTotalHitCount;

    public void setBoardTotalHitCount(int boardTotalHitCount) {
        this.boardTotalHitCount = boardTotalHitCount;
    }

    public void setFanartTotalHitCount(int fanartTotalHitCount) {
        this.fanartTotalHitCount = fanartTotalHitCount;
    }
}
