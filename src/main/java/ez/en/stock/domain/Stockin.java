package ez.en.stock.domain;

import ez.en.login.domain.Login;
import ez.en.order.domain.Order;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Stockin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sino;

    @Column
    private LocalDateTime sidate;

    @Column
    private LocalDateTime siidate;

    @Column
    private String siiper;

    @ManyToOne
    @JoinColumn(name = "ono")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "email")
    private Login login;
}
