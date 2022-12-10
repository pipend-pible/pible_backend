package com.pible.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "board", schema = "pible")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(allocationSize = 1, name = "boardSequence", sequenceName = "board_sequence", schema = "pible")
public class BoardEntity extends BaseEntity{

    @Id
    @GeneratedValue(generator = "boardSequence")
    @Column(name = "board_id")
    private Long id;
    private String title;
    private String content;
    private Integer likeCount;
    private Integer disLikeCount;
    private Integer hitCount;
    private String anonymousYn;
    private String displayYn;
    private String adultYn;
//
//    @Builder.Default
//    @OneToMany(mappedBy = "boardEntity", fetch = FetchType.LAZY)
//    private List<CommentEntity> commentEntityList = new ArrayList<>();

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
}
