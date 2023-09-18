package ez.en.order.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import ez.en.order.domain.Orders;
import ez.en.order.domain.QOrders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
public class OrderSearchImpl extends QuerydslRepositorySupport implements OrderSearch {

    public OrderSearchImpl(){
        super(Orders.class);
    }

    @Override
    public Page<Orders> search(String[] types, String keyword, Pageable pageable) {
        QOrders orders = QOrders.orders;
        JPQLQuery<Orders> query = from(orders);
        if((types!=null && types.length>0) && keyword!=null){
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for(String type:types){
                switch (type){
                    case "p":
                        booleanBuilder.or(orders.pname.contains(keyword));
                        break;
                    case "t":
                        booleanBuilder.or(orders.ptname.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }
        query.where(orders.ono.gt(0));
        this.getQuerydsl().applyPagination(pageable, query);
        List<Orders> list = query.fetch();
        long count=query.fetchCount();
        return new PageImpl<>(list, pageable, count);
    }
}
