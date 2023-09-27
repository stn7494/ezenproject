package ez.en.stock.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import ez.en.stock.domain.QStock;
import ez.en.stock.domain.QStockin;
import ez.en.stock.domain.Stock;
import ez.en.stock.domain.Stockin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class StockInSearchImpl extends QuerydslRepositorySupport implements StockInSearch {

    public StockInSearchImpl() {
        super(Stockin.class);
    }

    @Override
    public Page<Stockin> searchIn(String[] types, String keyword,Pageable pageable) {
        QStockin stockin = QStockin.stockin;
        JPQLQuery<Stockin> query = from(stockin);

        if ((types != null && types.length > 0) && keyword != null) {

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for (String type : types) {

                switch (type) {
                    case "t":
                        booleanBuilder.or(stockin.product.pcode.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(stockin.product.pname.contains(keyword));
                        break;
                    case "d":
                        booleanBuilder.or(stockin.sidate.contains(keyword));
                        break;
                }
            }//end for
            query.where(booleanBuilder);
        }//end if

        //sno > 0
        query.where(stockin.sino.gt(0));

        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Stockin> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list,pageable,count);
    }

}
