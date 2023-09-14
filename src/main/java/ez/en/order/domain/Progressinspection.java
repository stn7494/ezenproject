package ez.en.order.domain;

import ez.en.login.domain.Login;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Progressinspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pino;

    @Column
    private LocalDateTime pidate;

    @Column
    private String piper;

    @ManyToOne
    @JoinColumn(name = "email")
    private Login login;

    @ManyToOne
    @JoinColumn(name = "ono")
    private Order order;

}
