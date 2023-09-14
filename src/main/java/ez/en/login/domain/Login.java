package ez.en.login.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Login {

    @Id
    private String email;

    @Column
    private String name;

    @Column
    private String pw;

}
