package com.pible.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
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
    private String anonymousYn;
    private Integer likeCount;
    private Integer claimCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fanart_id")
    private FanartEntity fanartEntity;

}
