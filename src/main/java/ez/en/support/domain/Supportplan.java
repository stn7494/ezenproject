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
    private LocalDateTime spdate;

    @Column
    private int spcount;

    @Column
    private LocalDateTime spdelidate;

    @ManyToOne
    @JoinColumn(name = "pno")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "mcode")
    private Middle middle;


}
