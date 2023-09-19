package ez.en.support.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractDTO {

    private int cno;

    private String ccode;

    private String cdate;

    private int cprice;

    private String ccontent;

    private String cstate;

    private PartnerDTO partnerdto;

    private ProductDTO productdto;

    private MiddleDTO middledto;

    private TopDTO topdto;
}
