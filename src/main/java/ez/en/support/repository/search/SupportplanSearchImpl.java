package ez.en.support.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import ez.en.support.domain.QSupportplan;
import ez.en.support.domain.Supportplan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class SupportplanSearchImpl extends QuerydslRepositorySupport implements SupportplanSearch{

    public SupportplanSearchImpl(){super(Supportplan.class);}

    @Override
    public Page<Supportplan> search(Pageable pageable, String keyword, String type) {
        QSupportplan supportplan = QSupportplan.supportplan;

        JPQLQuery<Supportplan> query = from(supportplan);

        BooleanBuilder builder = new BooleanBuilder();

        if(type != null && keyword != null){
            switch (type){
                case "a":

            }
        }

        return null;

    }
}
