package ez.en.stock.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Dailystock extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int dsno;

    @Column
    private String dscode;

    @Column
    private String dsname;

    @Column
    private int dscount;

}
