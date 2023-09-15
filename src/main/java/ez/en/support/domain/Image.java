package ez.en.support.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ino;

    @Column
    private String iname;

    @Column
    private String ipath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pno")
    private Product product;

}
