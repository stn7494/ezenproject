package ez.en.order.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Orderstate {

    @Id
    private String oscode;

    @Column
    private String osname;
}
