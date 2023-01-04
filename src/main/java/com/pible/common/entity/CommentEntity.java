package com.pible.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "comment", schema = "pible")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@SequenceGenerator(allocationSize = 1, name = "commentSequence", sequenceName = "comment_sequence", schema = "pible")
public class CommentEntity extends BaseEntity{

    @Id
    @GeneratedValue(generator = "commentSequence")
    @Column(name = "comment_id")
    private Long id;
    private String content;
    @ColumnDefault("'N'")
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

    public void like() {
        this.likeCount += 1;
    }
}
