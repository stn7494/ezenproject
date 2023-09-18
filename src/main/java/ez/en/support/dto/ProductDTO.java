package ez.en.support.dto;


import jdk.jshell.execution.LocalExecutionControl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private int pno;
    private String pcode;
    private String pname;
    private String pcontent;
    private String pnote;
    private LocalDateTime pdate;
    private LocalDateTime pmoddate;
    private MiddleDTO middleDTO;
    private TopDTO topDTO;

}
