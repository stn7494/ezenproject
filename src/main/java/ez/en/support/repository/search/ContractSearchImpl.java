package ez.en.support.repository.search;

import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;
import ez.en.support.domain.Contract;
import ez.en.support.dto.ContractPageRequestDTO;
import ez.en.support.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContractSearchImpl implements ContractSearch{

    @Autowired
    private ContractRepository contractRepository;


    @Override
    public Page<Contract> search(String keyword, String type, String state, Pageable pageable) {

        if(type != null && keyword!= null && state!= null){
            state = state.equals("complete")?"계약완료":"계약종료";
                switch (type){
                    case "ccode":
                        return contractRepository.ccodeSearch(keyword,pageable,state);
                    case "pcode":
                        return contractRepository.pcodeSearch(keyword,pageable,state);
                    case "pname":
                        return contractRepository.pnameSearch(keyword,pageable,state);
                    case "ptname":
                        return contractRepository.ptnameSearch(keyword,pageable,state);

            }

        }
        return contractRepository.list(pageable);
    }

    @Override
    public PageResponseDTO<Contract> list(ContractPageRequestDTO pageRequestDTO) {

        String keyword = pageRequestDTO.getKeyword();

        String type = pageRequestDTO.getType();

        String state = pageRequestDTO.getState();

        Pageable pageable = pageRequestDTO.getPageable("cno");

        Page<Contract> result = search(keyword,type,state,pageable);

        List<Contract> list = result.getContent();
        return PageResponseDTO.<Contract>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(list)
                .total((int)result.getTotalElements())
                .build();
    }
}
