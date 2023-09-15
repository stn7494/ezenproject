package ez.en.support.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pno")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mcode")
    private Middle middle;


}
