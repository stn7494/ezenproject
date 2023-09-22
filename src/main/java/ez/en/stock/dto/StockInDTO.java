package ez.en.stock.dto;

import ez.en.order.domain.Orders;
import ez.en.support.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockInDTO {

    private int sino;
    private int scount;
    private String sidate;
    private String siidate;
    private String siicontent;
    private int ono;
    private String email;
    private Orders order;
    private Product product;
}
