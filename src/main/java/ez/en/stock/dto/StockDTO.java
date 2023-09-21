package ez.en.stock.dto;

import ez.en.order.dto.OrderDTO;
import ez.en.support.dto.ContractDTO;
import ez.en.support.dto.MiddleDTO;
import ez.en.support.dto.ProductDTO;
import ez.en.support.dto.TopDTO;
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
    private ContractDTO contractDTO;
    private MiddleDTO middleDTO;
    private TopDTO topDTO;
}
