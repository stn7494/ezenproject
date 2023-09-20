package ez.en.support.repository.search;

import ez.en.support.domain.Supportplan;
import ez.en.support.repository.SupportplanRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupportplanSearch{

    Page<Supportplan> search(Pageable pageable, String keyword, String type);

}
