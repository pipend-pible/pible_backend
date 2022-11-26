package com.pible.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "fanart_category", schema = "pible")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@SequenceGenerator(allocationSize = 1, name = "FanartCategorySequence", sequenceName = "fanart_category_sequence", schema = "pible")
public class FanartCategoryEntity extends BaseEntity{

    @Id
    @GeneratedValue(generator = "FanartCategorySequence")
    @Column(name = "fanart_category_id")
    private String id;
    private String categoryName;
}
