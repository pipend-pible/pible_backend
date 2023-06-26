package com.pible.common.entity;

import com.pible.common.converter.YNConverter;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "comment", schema = "pible")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(allocationSize = 1, name = "commentSequence", sequenceName = "comment_sequence", schema = "pible")
public class CommentEntity extends BaseEntity{

    @Id
    @GeneratedValue(generator = "commentSequence")
    @Column(name = "comment_id")
    private Long id;
    private String content;

    @ColumnDefault("'N'")
    @Convert(converter = YNConverter.class)
    private String anonymousYn;

    @ColumnDefault("0")
    private Integer likeCount;
    @ColumnDefault("0")
    private Integer claimCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fanart_id")
    private FanartEntity fanartEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public void setEntityRelation(BoardEntity boardEntity, FanartEntity fanartEntity, UserEntity userEntity) {
        this.boardEntity = boardEntity;
        this.fanartEntity = fanartEntity;
        this.userEntity = userEntity;
    }
    public void like() {
        this.likeCount += 1;
    }
    public void claim() {
        this.claimCount += 1;
    }
}
