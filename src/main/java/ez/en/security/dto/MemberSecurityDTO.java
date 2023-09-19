package ez.en.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@ToString
public class MemberSecurityDTO extends User {

    private String email;
    private String pw;
    private String name;

    public MemberSecurityDTO(String email, String pw, String name, Collection<? extends GrantedAuthority> authorities) {

        super(email,pw,authorities);

        this.email = email;
        this.pw = pw;
        this.name = name;
    }
}
