package com.pible.common.entity;

import com.pible.common.converter.YNConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import static com.pible.common.Constants.NO;

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
    private String imageUrl;
    private String oriImageName;

    @Convert(converter = YNConverter.class)
    @Builder.Default
    private String repImageYn = NO;

    @Convert(converter = YNConverter.class)
    private String thumbnailYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fanart_id")
    private FanartEntity fanartEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

}
