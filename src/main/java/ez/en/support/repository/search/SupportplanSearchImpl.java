package ez.en.support.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import ez.en.support.domain.QSupportplan;
import ez.en.support.domain.Supportplan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class SupportplanSearchImpl extends QuerydslRepositorySupport implements SupportplanSearch{

    public SupportplanSearchImpl(){super(Supportplan.class);}

    @Override
    public Page<Supportplan> search(Pageable pageable, String keyword, String[] type) {
        QSupportplan supportplan = QSupportplan.supportplan;

        JPQLQuery<Supportplan> query = from(supportplan);

        BooleanBuilder builder = new BooleanBuilder();

        if(type != null && keyword != null){
            switch (type[0]){
                case "a":
                    builder.or(supportplan.product.pcode.contains(keyword))
                            .or(supportplan.product.pname.contains(keyword))
                            .or(supportplan.middle.top.tname.contains(keyword));
                    break;
                case "c":
                    builder.or(supportplan.product.pcode.contains(keyword));
                    break;
                case "n":
                    builder.or(supportplan.product.pname.contains(keyword));
                    break;
                case "t":
                    builder.or(supportplan.middle.top.tname.contains(keyword));
                    break;
            }
        }

        query.where(supportplan.spstate.eq("조달요청"));

        this.getQuerydsl().applyPagination(pageable, query);

        List<Supportplan> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list,pageable,count);

    }
}
