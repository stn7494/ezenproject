package ez.en.order.domain;

import ez.en.login.domain.Login;
import ez.en.support.domain.Contract;
import ez.en.support.domain.Supportplan;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ono;

    @Column
    private String ocode;

    @Column
    private int ocount;

    @Column
    private LocalDateTime odate;

    @Column
    private LocalDateTime odelidate;

    @Column
    private String odetail;

    @Column
    private String ostate;

    @ManyToOne
    @JoinColumn(name = "cno")
    private Contract contract;

    @ManyToOne
    @JoinColumn(name = "spno")
    private Supportplan supportplan;


    @ManyToOne
    @JoinColumn(name = "email")
    private Login login;
}
