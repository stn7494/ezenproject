package ez.en.support.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import ez.en.support.domain.*;
import ez.en.support.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContractSearchImpl extends QuerydslRepositorySupport implements ContractSearch{

    public ContractSearchImpl(){super(Contract.class);}

    @Autowired
    private ContractRepository contractRepository;


    @Override
    public Page<Contract> search(String keyword, String type, String state, Pageable pageable) {

        QContract contract = QContract.contract;

        BooleanBuilder builder = new BooleanBuilder();

        JPQLQuery<Contract> query = from(contract);

        if(type != null && keyword!= null && state!= null){
            if(state.equals("all") && type.equals("all")){
                builder.or(contract.ccode.contains(keyword))
                        .or(contract.product.pcode.contains(keyword))
                        .or(contract.product.pname.contains(keyword))
                        .or(contract.partner.ptname.contains(keyword));
                query.where(builder);
            }else if(!(type.equals("all")) && state.equals("all")){
                switch (type){
                    case "ccode":
                        builder.or(contract.ccode.contains(keyword));
                        break;
                    case "pcode":
                        builder.or(contract.product.pcode.contains(keyword));
                        break;
                    case "pname":
                        builder.or(contract.product.pname.contains(keyword));
                        break;
                    case "ptname":
                        builder.or(contract.partner.ptname.contains(keyword));
                        break;
                }
                query.where(builder);
            }else if(!(state.equals("all")) && type.equals("all")){
                state = state.equals("complete")?"계약완료":"계약종료";
                builder.or(contract.ccode.contains(keyword))
                        .or(contract.product.pcode.contains(keyword))
                        .or(contract.product.pname.contains(keyword))
                        .or(contract.partner.ptname.contains(keyword))
                        .and(contract.cstate.eq(state));
                query.where(builder);
            }else {
                state = state.equals("complete") ? "계약완료" : "계약종료";
                switch (type) {
                    case "ccode":
                        builder.or(contract.ccode.contains(keyword).and(contract.cstate.eq(state)));
                        break;
                    case "pcode":
                        builder.or(contract.product.pcode.contains(keyword).and(contract.cstate.eq(state)));
                        break;
                    case "pname":
                        builder.or(contract.product.pname.contains(keyword).and(contract.cstate.eq(state)));
                        break;
                    case "ptname":
                        builder.or(contract.partner.ptname.contains(keyword).and(contract.cstate.eq(state)));
                        break;
                }
                query.where(builder);
            }
        }

        this.getQuerydsl().applyPagination(pageable, query);

        List<Contract> list =  query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list,pageable,count);
    }

    @Override
    public Page<Contract> search(String keyword, String type, Pageable pageable) {

        QContract contract = QContract.contract;

        BooleanBuilder builder = new BooleanBuilder();

        JPQLQuery<Contract> query = from(contract);

        if(type != null && keyword!= null){
            if(type.equals("all")){
                builder.or(contract.product.pcode.contains(keyword))
                        .or(contract.product.pname.contains(keyword));
                query.where(builder);
            }else{
                switch (type){
                    case "pcode":
                        builder.or(contract.product.pcode.contains(keyword));
                        break;
                    case "pname":
                        builder.or(contract.product.pname.contains(keyword));
                        break;
                }
                query.where(builder);
            }
        }
        query.where(contract.cstate.eq("계약완료"));

        this.getQuerydsl().applyPagination(pageable, query);

        List<Contract> list =  query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list,pageable,count);
    }

    @Override
    public List<Contract> selectOne(String keyword) {

        QContract contract = QContract.contract;

        JPQLQuery<Contract> query = from(contract);

        query.where(contract.ccode.eq(keyword));

        List<Contract> list = query.fetch();

        return list;
    }
}
