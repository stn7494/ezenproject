package ez.en.order.domain;

import ez.en.login.domain.Login;
import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(exclude = {"login", "orders"})
public class Progressinspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pino;

    @Column
    private String pidate;

    @Column
    private int pisequence;

    @Column
    private String pidetail;

    @Column
    private int piprogress;

    @Column
    private int picomplete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private Login login;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ono")
    private Orders orders;

    public void change(String pidate, String pidetail, int piprogress, int picomplete, Login login){
        this.pidate=pidate;
        this.pidetail=pidetail;
        this.piprogress=piprogress;
        this.picomplete=picomplete;
        this.login=login;
    }


}
