package com.taco.tacoshop.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.taco.tacoshop.domain.Item;
import com.taco.tacoshop.domain.ItemStatus;
import com.taco.tacoshop.domain.QItem;
import com.taco.tacoshop.domain.QItemImg;
import com.taco.tacoshop.dto.ItemSearchDto;
import com.taco.tacoshop.dto.MainItemDto;
import com.taco.tacoshop.dto.QMainItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public ItemRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    private BooleanExpression itemNameLike(String searchQuery){
        return StringUtils.isEmpty(searchQuery) ? null : QItem.item.itemName.like("%" + searchQuery + "%");
    }
    private BooleanExpression searchStatusEq(ItemStatus searchStatus){
        return searchStatus == null ? null : QItem.item.itemStatus.eq(searchStatus);
    }

    private BooleanExpression regDtsAfter(String searchDateType){
        LocalDateTime dateTime = LocalDateTime.now();

        switch (searchDateType) {
            case "1d" : dateTime = dateTime.minusDays(1); break;
            case "1w" : dateTime = dateTime.minusWeeks(1); break;
            case "1m" : dateTime = dateTime.minusMonths(1); break;
            case "6m" : dateTime = dateTime.minusMonths(6); break;
            default: return null; //all, null
        }
        return QItem.item.regTime.after(dateTime);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery){

        if (searchBy.equals("itemName")){
            return QItem.item.itemName.like("%" + searchQuery + "%");
        } else if (searchBy.equals("createBy")) {
            return QItem.item.itemName.like("%" + searchQuery + "%");
        } else {
            return null;
        }
       /* switch (searchBy){
            case "itemName" : return QItem.item.itemName.like("%" + searchQuery + "%");
            break;
            case "createBy" : return QItem.item.createdBy.like("%" + searchQuery + "%");
            break;
            default: return null;
        }*/

    }
    @Override
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        List<Item> content = queryFactory
                .selectFrom(QItem.item)
                .where(regDtsAfter(itemSearchDto.getSearchDateType()),
                        searchStatusEq(itemSearchDto.getSearchStatus()),
                        searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))
                .orderBy(QItem.item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = content.size();


        return new PageImpl<>(content,pageable,total);
    }

    @Override
    public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto,Pageable pageable){
            QItem item = QItem.item;
            QItemImg itemImg = QItemImg.itemImg;

            List<MainItemDto> content = queryFactory
                    .select(
                            new QMainItemDto(
                                    item.id, item.itemName,item.itemDetail,itemImg.imgUrl,item.price)
                    )
                    .from(itemImg)
                    .join(itemImg.item, item)
                    .where(itemImg.repimgYn.eq("Y"),
                            itemNameLike(itemSearchDto.getSearchQuery()))
                    .orderBy(item.id.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            Long total = queryFactory
                    .select(item.count())
                    .from(itemImg)
                    .join(itemImg.item, item)
                    .where(itemImg.repimgYn.eq("Y"),
                            itemNameLike(itemSearchDto.getSearchQuery()))
                    .fetchOne();

            return new PageImpl<>(content, pageable, total);

    }



























}
