package ez.en.order.domain;

import ez.en.login.domain.Login;
import ez.en.support.domain.Contract;
import ez.en.support.domain.Supportplan;
import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(exclude = {"contract", "supportplan", "login"})
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ono;

    @Column
    private String ocode;

    @Column
    private int ocount;

    @Column
    private String odate;

    @Column
    private String odelidate;

    @Column
    private String odetail;

    @Column
    private String ostate;

    @Column
    private String ccode;

    @Column
    private String pname;

    @Column
    private String ptname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cno")
    private Contract contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spno")
    private Supportplan supportplan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private Login login;



//    발주 상태 수정 메소드
    public void changeState(String ostate){
        this.ostate = ostate;
    }

//    발주 상세 내용 수정 메소드
    public void changeDetail(String odetail){
        this.odetail = odetail;
    }


}
