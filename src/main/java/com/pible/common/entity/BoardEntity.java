package com.pible.common.entity;

import com.pible.common.converter.YNConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import static com.pible.common.Constants.NO;
import static com.pible.common.Constants.YES;

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
    private int likeCount;
    private int disLikeCount;
    private int hitCount;
    @Convert(converter = YNConverter.class)
    @Builder.Default
    private String anonymousYn = NO;

    @Convert(converter = YNConverter.class)
    @Builder.Default
    private String displayYn = YES;

    @Convert(converter = YNConverter.class)
    @Builder.Default
    private String adultYn = NO;

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
