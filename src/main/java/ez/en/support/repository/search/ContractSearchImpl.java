package ez.en.support.repository.search;

import com.querydsl.jpa.JPQLQuery;
import ez.en.support.domain.Contract;
import ez.en.support.domain.Partner;
import ez.en.support.domain.QPartner;
import ez.en.support.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContractSearchImpl extends QuerydslRepositorySupport implements ContractSearch{

    public ContractSearchImpl(){super(Partner.class);}

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
    public Page<Partner> search(String keyword, String type, Pageable pageable) {

        QPartner partner = QPartner.partner;

        JPQLQuery<Partner> query = from(partner);

        if(keyword!=null && type!=null){
            switch (type){
                case "ptname":
                    query.where(partner.ptname.contains(keyword));
                    break;
                case "ptceoname":
                    query.where(partner.ptceoname.contains(keyword));
                    break;
            }
        }

        this.getQuerydsl().applyPagination(pageable,query);

        List<Partner> list = query.fetch();

        long count = query.fetchCount();
        return new PageImpl<>(list,pageable,count);
    }


}
