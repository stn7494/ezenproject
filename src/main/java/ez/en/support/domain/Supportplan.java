package ez.en.support.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(exclude = {"product","middle"})
public class Supportplan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int spno;

    @Column
    private String spdate;

    @Column
    private int spcount;

    @Column
    private String spdelidate;

    @Column
    private String spstate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pno")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mcode")
    private Middle middle;

    public void update(String spdate, int spcount, String spdelidate){
        this.spdate = spdate;
        this.spcount = spcount;
        this.spdelidate = spdelidate;
    }


    public void changeState(String spstate){
        this.spstate = spstate;
    }


}
