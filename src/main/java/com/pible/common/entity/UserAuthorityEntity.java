package com.pible.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "user_authority", schema = "pible")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@SequenceGenerator(allocationSize = 1, name = "userAuthoritySequence", sequenceName = "user_sequence", schema = "pible")
public class UserAuthorityEntity {

    @Id
    @GeneratedValue(generator = "userSequence")
    @Column(name = "user_authority_id")
    private long id;

    @ManyToOne
    private UserEntity userEntity;

    @ManyToOne
    private AuthorityEntity authorityEntity;

    public UserAuthorityEntity(UserEntity userEntity, AuthorityEntity authorityEntity) {
        this.userEntity = userEntity;
        this.authorityEntity = authorityEntity;
    }
}
