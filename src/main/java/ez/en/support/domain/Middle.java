package ez.en.support.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Middle {

    @Id
    private String mcode;

    @Column
    private String mname;

    @ManyToOne
    @JoinColumn(name = "tcode")
    private Top top;
}
