package ez.en.support.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(exclude = "top")
public class Middle {

    @Id
    private String mcode;

    @Column
    private String mname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tcode")
    private Top top;
}
