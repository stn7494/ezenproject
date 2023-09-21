package ez.en.order.domain;

import ez.en.login.domain.Login;
import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(exclude = {"login", "orders"})
@Table(name = "progressinspection")
public class ProgressInspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pino;

    @Column
    private String pidate;

    @Column
    private int pisequence;

    @Column
    private String pidetail;

    @Column
    private boolean picomplete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private Login login;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ono")
    private Orders orders;


}
