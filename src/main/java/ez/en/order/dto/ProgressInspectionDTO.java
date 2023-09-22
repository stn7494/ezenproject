package ez.en.order.dto;

import ez.en.login.domain.Login;
import ez.en.order.domain.Orders;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProgressInspectionDTO {

    private int pino;

    private String pidate;

    private int pisequence;

    private String pidetail;

    private int picomplete;

    private int piprogress;

    private String email;

    private int ono;

    private Login login;

    private Orders orders;

}
