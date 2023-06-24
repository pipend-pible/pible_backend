package com.pible.common.entity;

import com.pible.common.converter.PasswordConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user", schema = "pible")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@SequenceGenerator(allocationSize = 1, name = "userSequence", sequenceName = "user_sequence", schema = "pible")
public class UserEntity extends BaseEntity{

    @Id
    @GeneratedValue(generator = "userSequence")
    @Column(name = "user_id")
    private Long id;
    @Column(unique = true)
    private String email;

    @Convert(converter = PasswordConverter.class)
    private String password;

    @Column(unique = true)
    private String nickName;
    private String verifyAgeYn;
    private String emailCheckYn;
    private String privacyAgreeYn;
    private String usageYn;
    private String userState;
    private String bankAccountYn;
    private LocalDateTime deleteDate;
    private String profileMsg;

    public void setUserEmail(String email) {
        this.email = email;
    }
}
