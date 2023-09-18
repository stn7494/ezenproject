package ez.en.support.service;

import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;
import ez.en.support.domain.Contract;
import ez.en.support.dto.ContractPageRequestDTO;

public interface ContractService {

    PageResponseDTO<Contract> list(ContractPageRequestDTO pageRequestDTO);

}
