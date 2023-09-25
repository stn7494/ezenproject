package ez.en.stock.domain;

import ez.en.order.dto.OrderDTO;
import ez.en.stock.dto.StockOutDTO;
import ez.en.support.domain.Contract;
import ez.en.support.domain.Product;
import ez.en.support.domain.Supportplan;
import ez.en.support.dto.ContractDTO;
import ez.en.support.dto.MiddleDTO;
import ez.en.support.dto.ProductDTO;
import ez.en.support.dto.TopDTO;
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
    private int sicountall;

    @Column
    private int socountall;

    @Column
    private String snote;

    @ManyToOne
    @JoinColumn(name = "pno")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cno")
    private Contract contract;
}
