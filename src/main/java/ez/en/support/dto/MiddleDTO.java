package ez.en.support.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MiddleDTO {

    private String mcode;
    private String mname;
    private TopDTO topDTO;
}
