package com.pible.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "board_category", schema = "pible")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@SequenceGenerator(allocationSize = 1, name = "BoardCategorySequence", sequenceName = "board_category_sequence", schema = "pible")
public class BoardCategoryEntity extends BaseEntity{

    @Id
    @GeneratedValue(generator = "BoardCategorySequence")
    @Column(name = "board_category_id")
    private String id;
    private String categoryName;
}
