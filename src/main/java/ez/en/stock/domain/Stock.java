package ez.en.stock.domain;

import ez.en.support.domain.Contract;
import ez.en.support.domain.Product;
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
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sno;

    @Column
    private int scount;

    @Column
    private String snote;

    @ManyToOne
    @JoinColumn(name = "pno")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cno")
    private Contract contract;

}
