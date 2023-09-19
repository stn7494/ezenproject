package ez.en.order.dto;

import ez.en.login.domain.Login;
import ez.en.support.domain.Contract;
import ez.en.support.domain.Supportplan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private int ono;

    private String ocode;

    private int ocount;

    private String odate;

//    private String odelidate;

    private String odetail;

    private String ostate;

    private int cno;

    private int spno;

    private String email;

    private Contract contract;

    private Supportplan supportplan;

    private Login login;


}
