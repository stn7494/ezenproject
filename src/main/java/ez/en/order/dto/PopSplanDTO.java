package ez.en.order.dto;

import ez.en.support.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//임시 테스트용 DTO 클래스
public class PopSplanDTO {

    private int spno;

    private String spdelidate;

    private String spstate;

    private int spcount;

    private Product product;





}
