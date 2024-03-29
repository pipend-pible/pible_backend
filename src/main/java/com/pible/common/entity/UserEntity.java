package com.pible.common.entity;

import com.pible.common.converter.PasswordConverter;
import com.pible.common.converter.YNConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.pible.common.Constants.NO;

@Entity
@Table(name = "user", schema = "pible")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@SequenceGenerator(allocationSize = 1, name = "accountSequence", sequenceName = "account_sequence", schema = "pible")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(generator = "accountSequence")
    @Column(name = "user_id")
    private Long id;
    @Column(unique = true)
    private String email;

    @Convert(converter = PasswordConverter.class)
    private String password;

    @Column(unique = true)
    private String nickName;

    @Convert(converter = YNConverter.class)
    @Builder.Default
    private String verifyAgeYn = NO;

    @Convert(converter = YNConverter.class)
    @Builder.Default
    private String privacyAgreeYn = NO;
    private String userState;

    @Convert(converter = YNConverter.class)
    @Builder.Default
    private String bankAccountYn = NO;

    private LocalDateTime deleteDate;
    private String profileMsg;

    public void setUserEmail(String email) {
        this.email = email;
    }
}
