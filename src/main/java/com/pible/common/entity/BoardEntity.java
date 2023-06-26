package com.pible.common.entity;

import com.pible.common.converter.YNConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "board", schema = "pible")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Where(clause = "delete_yn = 'N'")
@SequenceGenerator(allocationSize = 1, name = "boardSequence", sequenceName = "board_sequence", schema = "pible")
public class BoardEntity extends BaseEntity{

    @Id
    @GeneratedValue(generator = "boardSequence")
    @Column(name = "board_id")
    private Long id;
    private String title;
    private String content;
    @ColumnDefault("0")
    private int likeCount;
    @ColumnDefault("0")
    private int disLikeCount;
    @ColumnDefault("0")
    private int hitCount;
    @ColumnDefault("'N'")
    @Convert(converter = YNConverter.class)
    private String anonymousYn;

    @ColumnDefault("'N'")
    @Convert(converter = YNConverter.class)
    private String displayYn;

    @ColumnDefault("'N'")
    @Convert(converter = YNConverter.class)
    private String adultYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id")
    private ChannelEntity channelEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_category_id")
    private BoardCategoryEntity boardCategoryEntity;

    public void setRelation(ChannelEntity channelEntity, UserEntity userEntity, BoardCategoryEntity boardCategoryEntity) {
        this.channelEntity = channelEntity;
        this.userEntity = userEntity;
        this.boardCategoryEntity = boardCategoryEntity;
    }

    public void like() {
        this.likeCount += 1;
    }
    public void plusHitCount() {
        this.hitCount += 1;
    }
}
