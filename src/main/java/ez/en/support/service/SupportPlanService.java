package ez.en.support.service;

import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;
import ez.en.support.domain.Supportplan;
import ez.en.support.dto.SupportPlanDTO;

public interface SupportPlanService {

    PageResponseDTO<Supportplan> list(PageRequestDTO requestDTO);

    void insert(SupportPlanDTO dto);

    SupportPlanDTO selectOne(int spno);

    void update(SupportPlanDTO dto);


//    발주시 조달 상태 수정용 메소드
    void updateState(Supportplan supportplan);

}
