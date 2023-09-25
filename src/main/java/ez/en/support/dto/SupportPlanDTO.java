package ez.en.support.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupportPlanDTO {

    private int spno;

    private String spdate;

    private int spcount;

    private String spdelidate;

    private String spstate;

    private ProductDTO productDTO;

    private MiddleDTO middleDTO;
}
