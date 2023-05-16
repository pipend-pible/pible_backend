package com.pible.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "authority", schema = "pible")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class AuthorityEntity implements Persistable<String> {

    @Id
    private String authority;
    private String authCd;
    private LocalDateTime createDate;

    @Override
    public String getId() {
        return authCd;
    }

    @Override
    public boolean isNew() {
        return createDate == null;
    }
}
