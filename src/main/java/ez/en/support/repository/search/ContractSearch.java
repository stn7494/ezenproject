package ez.en.support.repository.search;

import ez.en.config.PageResponseDTO;
import ez.en.support.domain.Contract;
import ez.en.support.dto.ContractPageRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContractSearch {

    Page<Contract> search(String keyword, String type, String state ,Pageable pageable);

    PageResponseDTO<Contract> list(ContractPageRequestDTO pageRequestDTO);
}
