package ez.en.support.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import ez.en.support.domain.Partner;
import ez.en.support.domain.QPartner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PartnerSearchImpl extends QuerydslRepositorySupport implements PartnerSearch {

    PartnerSearchImpl(){super(Partner.class);}

    @Override
    public Page<Partner> search(String keyword, String type, Pageable pageable) {

        QPartner partner = QPartner.partner;

        JPQLQuery<Partner> query = from(partner);

        BooleanBuilder builder = new BooleanBuilder();
        if(keyword!=null && type!=null){
            switch (type){
                case "all":
                    builder.or(partner.ptname.contains(keyword)).or(partner.ptceoname.contains(keyword));
                    query.where(builder);
                    break;
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
