package ez.en.stock.domain;

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
public class Stockout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sono;

    @Column
    private int socount;

    @Column
    private String sodate;

    @ManyToOne
    @JoinColumn(name = "sno")
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "email")
    private Login login;

}
