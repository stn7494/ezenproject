package ez.en.support.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductListAllDTO {

    private int pno;
    private String pcode;
    private String pname;
    private String pcontent;
    private LocalDateTime pDate;

    private List<ProductImageDTO> productImages;

}
