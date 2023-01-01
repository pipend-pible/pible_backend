package com.pible.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "fanart_category", schema = "pible")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@DynamicUpdate
@SequenceGenerator(allocationSize = 1, name = "FanartCategorySequence", sequenceName = "fanart_category_sequence", schema = "pible")
public class FanartCategoryEntity extends BaseEntity{

    @Id
    @GeneratedValue(generator = "FanartCategorySequence")
    @Column(name = "fanart_category_id")
    private Long id;
    private String categoryName;
}
