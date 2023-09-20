package ez.en.stock.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import ez.en.stock.domain.QStock;
import ez.en.stock.domain.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class StockSearchImpl extends QuerydslRepositorySupport implements StockSearch {

    public StockSearchImpl() {
        super(Stock.class);
    }

    @Override
    public Page<Stock> searchAll(String[] types, String keyword, Pageable pageable) {

        QStock stock = QStock.stock;
        JPQLQuery<Stock> query = from(stock);

        if ((types != null && types.length > 0) && keyword != null) {

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for (String type : types) {

                switch (type) {
                    case "t":
                        booleanBuilder.or(stock.product.pcode.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(stock.product.pname.contains(keyword));
                        break;
                }
            }//end for
            query.where(booleanBuilder);
        }//end if

        //sno > 0
        query.where(stock.sno.gt(0));

        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Stock> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list,pageable,count);
    }
}
