package ez.en.stock.dto;

import ez.en.order.domain.Orders;
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
    private String sidate;
    private String siidate;
    private String siicontent;
    private int ono;
    private String email;
    private Orders order;
}
