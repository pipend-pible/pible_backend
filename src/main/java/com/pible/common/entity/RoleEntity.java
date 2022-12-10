package com.pible.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "role", schema = "pible")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class RoleEntity extends BaseEntity{

    @Id
    @Column(name = "role_id")
    private String id;
    private String roleName;

    //TODO 역할마다 권한 관리할 컬럼 추가할건지 확인
}
