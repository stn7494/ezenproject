package ez.en.order.dto;

import ez.en.support.domain.Partner;
import ez.en.support.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PopContractDTO {

    private int cno;

    private String ccode;

    private int cprice;

    private String cstate;

    private Partner partner;

    private Product product;




}
