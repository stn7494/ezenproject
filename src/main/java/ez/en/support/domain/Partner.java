package ez.en.support.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ptno;

    @Column
    private String ptname;

    @Column
    private String ptceoname;

    @Column
    private String ptnum;

    @Column
    private String ptadd;

    @Column
    private String ptfax;
}
