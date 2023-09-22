package ez.en.stock.domain;

import ez.en.login.domain.Login;
import ez.en.order.domain.Orders;
import ez.en.support.domain.Product;
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
    private int sicount;

    @Column
    private String sidate;

    @Column
    private String siidate;

    @Column
    private String siicontent;

    @ManyToOne
    @JoinColumn(name = "ono")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "email")
    private Login login;

    @ManyToOne
    @JoinColumn(name = "pno")
    private Product product;
}
