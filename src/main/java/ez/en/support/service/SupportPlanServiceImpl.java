package ez.en.support.service;

import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;
import ez.en.support.domain.Supportplan;
import ez.en.support.dto.SupportPlanDTO;
import ez.en.support.repository.SupportplanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class SupportPlanServiceImpl implements SupportPlanService{

    private final ModelMapper modelMapper;

    private final SupportplanRepository repository;

    @Override
    public PageResponseDTO<Supportplan> list(PageRequestDTO requestDTO) {

        String[] types = requestDTO.getTypes();

        String keyword = requestDTO.getKeyword();

        Pageable pageable = requestDTO.getPageable("spno");

        Page<Supportplan> page = repository.search(pageable,keyword,types);

        List<Supportplan> list = page.getContent();

        return PageResponseDTO.<Supportplan>withAll()
                .dtoList(list)
                .total((int)page.getTotalElements())
                .pageRequestDTO(requestDTO)
                .build();
    }

    @Override
    public void insert(SupportPlanDTO dto) {
        Supportplan supportplan = modelMapper.map(dto, Supportplan.class);
        repository.save(supportplan);
    }

    @Override
    public SupportPlanDTO selectOne(int spno) {
        Supportplan supportplan = repository.selectOne(spno);

        SupportPlanDTO planDTO = modelMapper.map(supportplan, SupportPlanDTO.class);

        return planDTO;
    }

    @Override
    public void update(SupportPlanDTO dto) {
        Supportplan supportplan = repository.selectOne(dto.getSpno());
        supportplan.update(dto.getSpdate(),dto.getSpcount(),dto.getSpdelidate());
        repository.save(supportplan);
    }



//  발주시 조달 상태 수정용 메소드
    @Override
    public void updateState(Supportplan supportplan) {
        Supportplan supportplan1 = repository.selectOne(supportplan.getSpno());
        supportplan1.changeState(supportplan.getSpstate());
        repository.save(supportplan1);

    }
    @Override
    public void stateUpdate(int spno, String state) {
        Supportplan supportplan = repository.selectOne(spno);
        supportplan.stateUpdate(state);
        repository.save(supportplan);
    }

    @Override
    public void delete(int spno) {
        repository.deleteById(spno);

    }
}
