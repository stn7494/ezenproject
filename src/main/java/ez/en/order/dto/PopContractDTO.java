package ez.en.order.dto;

import ez.en.support.domain.Partner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//임시 테스트용 DTO 클래스
public class PopContractDTO {

    private int cno;

    private String ccode;

    private int cprice;

    private String cstate;

    private Partner partner;




}
