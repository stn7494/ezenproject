package ez.en.support.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(exclude = "middle")
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

    @Column
    private String filename;

    @Column
    private String filepath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mcode")
    private Middle middle;

    //수정항목 설정!
    public void productchange(String pname, String pcontent, String pnote) {
        this.pname = pname;
        this.pcontent = pcontent;
        this.pnote = pnote;
    }

}
