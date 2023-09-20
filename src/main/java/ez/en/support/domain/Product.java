package ez.en.support.domain;

import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mcode")
    private Middle middle;

    @OneToMany(mappedBy = "product",
    cascade = {CascadeType.ALL},
    fetch = FetchType.LAZY,
    orphanRemoval = true)  // ProductImage의 product 변수
    @Builder.Default
    @BatchSize(size=20)
    private Set<ProductImage> imageSet = new HashSet<>();

    public void addImage(String uuid, String fileName){
        ProductImage productImage = ProductImage.builder()
                .uuid(uuid)
                .fileName(fileName)
                .product(this)
                .ord(imageSet.size())
                .build();
        imageSet.add(productImage);
    }

    public void clearImages() {
        imageSet.forEach(productImage -> productImage.changeProduct(null));
        this.imageSet.clear();
    }

    //수정항목 설정!
    public void productchange(String pname, String pcontent, String pnote) {
        this.pname = pname;
        this.pcontent = pcontent;
        this.pnote = pnote;
    }

}
