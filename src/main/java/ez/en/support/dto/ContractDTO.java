package ez.en.support.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;


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

    private PartnerDTO partnerDTO;

    private ProductDTO productDTO;

}
