package ez.en.support.repository.search;

import ez.en.support.domain.Partner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PartnerSearch {

    Page<Partner> search(String keyword, String type, Pageable pageable);

}
