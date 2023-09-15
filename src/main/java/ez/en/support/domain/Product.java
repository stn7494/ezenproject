package ez.en.support.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pno;

    @Column
    private String pcode;

    @Column
    private String pname;

    @Column
    private String pcontent;

    @Column
    private String pnote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mcode")
    private Middle middle;

}
