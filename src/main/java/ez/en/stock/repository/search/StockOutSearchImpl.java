package ez.en.stock.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import ez.en.stock.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class StockOutSearchImpl extends QuerydslRepositorySupport implements StockOutSearch {

    public StockOutSearchImpl() {
        super(Stockout.class);
    }

    @Override
    public Page<Stockout> searchOut(String[] types, String keyword,Pageable pageable) {

        QStockout stockout = QStockout.stockout;
        JPQLQuery<Stockout> query = from(stockout);

        if ((types != null && types.length > 0) && keyword != null) {

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for (String type : types) {

                switch (type) {
                    case "t":
                        booleanBuilder.or(stockout.stock.product.pcode.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(stockout.stock.product.pname.contains(keyword));
                        break;
                    case "d":
                        booleanBuilder.or(stockout.sodate.contains(keyword));
                        break;
                }
            }//end for
            query.where(booleanBuilder);
        }//end if

        //sno > 0
        query.where(stockout.sono.gt(0));

        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Stockout> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list,pageable,count);
    }

}
