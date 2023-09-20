package ez.en.support.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import ez.en.support.domain.Product;
import ez.en.support.domain.QProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProductSearchImpl extends QuerydslRepositorySupport implements ProductSearch {
    public ProductSearchImpl() {
        super(Product.class);
    }

    @Override
    public Page<Product> productsearch1(Pageable pageable) {
        QProduct product = QProduct.product;
        JPQLQuery<Product> query = from(product);
        query.where(product.pname.contains("1"));
        //페이징 - 테스트 하고나면 콘솔에 limit ?, 생김
        this.getQuerydsl().applyPagination(pageable, query);

        List<Product> list = query.fetch();
        long count = query.fetchCount();
        return null;
    }

    @Override
    public Page<Product> productsearchAll(String[] types, String keyword, Pageable pageable) {
        QProduct product = QProduct.product;
        JPQLQuery<Product> query = from(product);

        // 검색조건과 키워드가 있다면
        if ((types != null && types.length > 0) && keyword != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for (String type : types) {
                switch (type) {
                    case "c":
                        booleanBuilder.or(product.pcode.contains(keyword));
                        break;
                    case "m":
                        booleanBuilder.or(product.pname.contains(keyword));
                        break;
                }
            }//end for문
            query.where(booleanBuilder);
        }//end if

        //bno > 0
        query.where(product.pno.gt(0));

        //paging
        this.getQuerydsl().applyPagination(pageable, query);
        List<Product> list = query.fetch();
        long count = query.fetchCount();
        return new PageImpl<>(list, pageable,count);
    }





}
