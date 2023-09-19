package ez.en.support.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartnerDTO {

    private int ptno;

    private String ptname;

    private String ptceoname;

    private String ptnum;

    private String ptadd;

    private String ptfax;
}
