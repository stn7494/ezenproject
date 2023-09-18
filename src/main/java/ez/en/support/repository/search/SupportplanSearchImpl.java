package ez.en.support.repository.search;

import com.querydsl.jpa.JPQLQuery;
import ez.en.support.domain.QSupportplan;
import ez.en.support.domain.Supportplan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class SupportplanSearchImpl extends QuerydslRepositorySupport implements SupportplanSearch{

    public SupportplanSearchImpl(){super(Supportplan.class);}

    @Override
    public Page<Supportplan> searchAll(Pageable pageable, String keyword, String[] types) {
        QSupportplan supportplan = QSupportplan.supportplan;

        JPQLQuery<Supportplan> query = from(supportplan);

        if((types != null && types.length > 0)&&keyword != null){
            switch (types[0]){
                case "c":

            }
        }

        return null;

    }
}
