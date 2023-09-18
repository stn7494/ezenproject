package ez.en.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private int ono;

    private String ocode;

    private int ocount;

    private String odate;

    private String odelidate;

    private String odetail;

    private String ostate;

    private int cno;

    private int spno;

    private String email;

    private String ptname;

    private String pname;

    private String ccode;


}
