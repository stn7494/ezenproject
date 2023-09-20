package ez.en.stock.dto;

import ez.en.order.dto.OrderDTO;
import ez.en.support.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockDTO {

    private int sno;
    private int scount;
    private String snote;
    private int cno;
    private int pno;
    private StockOutDTO stockOutDTO;
    private ProductDTO productDTO;
    private OrderDTO orderDTO;
}
