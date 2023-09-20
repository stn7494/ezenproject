package ez.en.support.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "product")
public class ProductImage implements Comparable<ProductImage> {

    @Id
    private String uuid;
    private String fileName;
    private int ord;

    @ManyToOne
    private Product product;

    @Override
    public int compareTo(ProductImage other) {
        return this.ord - other.ord;
    }

    public void changeProduct(Product product){
        this.product = product;
    }
}
