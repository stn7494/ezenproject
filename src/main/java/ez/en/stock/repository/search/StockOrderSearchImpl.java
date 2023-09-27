package ez.en.stock.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.jpa.JPQLQuery;
import ez.en.order.domain.Orders;
import ez.en.order.domain.QOrders;
import ez.en.stock.domain.QStockin;
import ez.en.stock.domain.Stockin;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class StockOrderSearchImpl extends QuerydslRepositorySupport implements StockOrderSearch {

    public StockOrderSearchImpl() {
        super(Orders.class);
    }

    @Override
    public Page<Orders> searchOrder(String[] types, String keyword,Pageable pageable) {
        QOrders orders = QOrders.orders;
        JPQLQuery<Orders> query = from(orders);

        if ((types != null && types.length > 0) && keyword != null) {

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for (String type : types) {

                switch (type) {
                    case "t":
                        booleanBuilder.or(orders.supportplan.product.pcode.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(orders.supportplan.product.pname.contains(keyword));
                        break;
                    case "d":
                        booleanBuilder.or(orders.odate.contains(keyword));
                        break;
                }
            }//end for
            query.where(booleanBuilder);
        }//end if

        //sno > 0
        query.where(orders.ono.gt(0));
        query.where(orders.ostate.eq("검수완료"));

        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Orders> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list,pageable,count);
    }

}
