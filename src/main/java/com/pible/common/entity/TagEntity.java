package com.pible.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "tag", schema = "pible")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@SequenceGenerator(allocationSize = 1, name = "tagSequence", sequenceName = "tag_sequence", schema = "pible")
public class TagEntity {

    @Id
    @GeneratedValue(generator = "tagSequence")
    @Column(name = "tag_id")
    private Long id;
    private String tag;
}
