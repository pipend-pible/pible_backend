package com.pible.common.entity;

import com.pible.common.converter.YNConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "fanart", schema = "pible")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@SequenceGenerator(allocationSize = 1, name = "fanartSequence", sequenceName = "fanart_sequence", schema = "pible")
public class FanartEntity extends BaseEntity {

    @Id
    @GeneratedValue(generator = "fanartSequence")
    @Column(name = "fanart_id")
    private Long id;
    private String title;
    private String content;
    @ColumnDefault("0")
    private int hitCount;
    @ColumnDefault("0")
    private int likeCount;
    @ColumnDefault("0")
    private int disLikeCount;
    @Convert(converter = YNConverter.class)
    private String myArtYn;

    @Convert(converter = YNConverter.class)
    private String displayYn;

    @Convert(converter = YNConverter.class)
    private String adultYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id")
    private ChannelEntity channelEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fanart_category_id")
    private FanartCategoryEntity fanartCategoryEntity;

    public void setRelation(ChannelEntity channelEntity, UserEntity userEntity, FanartCategoryEntity fanartCategoryEntity) {
        this.channelEntity = channelEntity;
        this.userEntity = userEntity;
        this.fanartCategoryEntity = fanartCategoryEntity;
    }

    public void plusHitCount() {
        this.hitCount += 1;
    }
}
