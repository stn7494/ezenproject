package ez.en.support.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(exclude = {"partner", "product"})
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cno;

    @Column
    private String ccode;

    @Column
    private String cdate;

    @Column
    private int cprice;

    @Column
    private String ccontent;

    @Column
    private String cstate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ptno")
    private Partner partner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pno")
    private Product product;

}
