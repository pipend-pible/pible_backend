package com.pible.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "image", schema = "pible")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@SequenceGenerator(allocationSize = 1, name = "imageSequence", sequenceName = "image_sequence", schema = "pible")
public class ImageEntity {

    @Id
    @GeneratedValue(generator = "imageSequence")
    @Column(name = "image_id")
    private Long id;
    @Column(name = "image_name")
    private String imageUrl;
    private String oriImageName;
    @ColumnDefault("'N'")
    private String repImageYn;
    private String thumbnailYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fanart_id")
    private FanartEntity fanartEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

}
