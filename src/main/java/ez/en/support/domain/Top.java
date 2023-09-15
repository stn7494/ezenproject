package ez.en.support.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Top {

    @Id
    private String tcode;

    @Column
    private String tname;


}
