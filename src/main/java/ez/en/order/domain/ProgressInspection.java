package ez.en.order.domain;

import ez.en.login.domain.Login;
import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "progressinspection")
public class ProgressInspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pino;

    @Column
    private String pidate;

    @Column
    private String piper;

    @ManyToOne
    @JoinColumn(name = "email")
    private Login login;

    @ManyToOne
    @JoinColumn(name = "ono")
    private Order order;

}
