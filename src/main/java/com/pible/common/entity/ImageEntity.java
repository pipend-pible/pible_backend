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
    private String imageName;
    private String oriImageName;
    private String imagePath;
    @ColumnDefault("'N'")
    private String repImageYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fanart_id")
    private FanartEntity fanartEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

}
