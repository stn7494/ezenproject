package ez.en.stock.dto;

import ez.en.stock.domain.Stock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockOutDTO {

    private int sono;
    private int socount;
    private String sodate;
    private int sno;
    private String email;
    private Stock stock;
}
