package ez.en.stock.dto;

import ez.en.order.dto.OrderDTO;
import ez.en.support.domain.Contract;
import ez.en.support.domain.Product;
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
    private int sicountall;
    private int socountall;
    private String snote;
    private Contract contract;
    private Product product;

}
