package com.pible.common.entity;

import com.pible.common.Constants;
import com.pible.common.converter.DateTimeConverter;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
// 기본적으로 모든 엔티티는 연관 관계가 있을 경우 DB와 동일하게 연관관계의 주인을 FK가 있는 쪽으로 설정하여 매핑하였습니다.
// auditing 정보가 필요한 엔티티들이 상속하는 엔티티 클래스입니다.
public class BaseEntity {

    @CreatedDate
    @Convert(converter = DateTimeConverter.class)
    private LocalDateTime createDate;

    @LastModifiedDate
    @Convert(converter = DateTimeConverter.class)
    private LocalDateTime updateDate;
    @ColumnDefault("'N'")
    private String deleteYn = Constants.NO;

    public void delete() {
        this.deleteYn = Constants.YES;
    }
}
