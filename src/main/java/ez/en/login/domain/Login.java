package ez.en.login.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(exclude = "roleSet")
public class Login {

    @Id
    private String email;

    @Column
    private String name;

    @Column
    private String pw;


    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void addRole(MemberRole memberRole) {
        this.roleSet.add(memberRole);
    }
    public void clearRoles() {
        this.roleSet.clear();
    }
}
