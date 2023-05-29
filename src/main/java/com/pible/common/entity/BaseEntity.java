package com.pible.common.entity;

import com.pible.common.converter.DateTimeConverter;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Where(clause = "del_yn = 'N'")
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @CreatedDate
    @Convert(converter = DateTimeConverter.class)
    private LocalDateTime createDate;
    @LastModifiedDate
    @Convert(converter = DateTimeConverter.class)
    private LocalDateTime updateDate;
    @ColumnDefault("'N'")
    private String deleteYn;

    public void delete() {
        this.deleteYn = "Y";
    }
}
