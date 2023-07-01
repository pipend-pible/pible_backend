package com.pible.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "board_category", schema = "pible")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@DynamicUpdate
@Where(clause = "delete_yn = 'N'")
public class BoardCategoryEntity extends BaseEntity{

    @Id
    @Column(name = "board_category_id")
    private Long id;
    private String categoryName;
}
