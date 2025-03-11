package com.jiwon.example.barogo.repository.querydsl;

import com.jiwon.example.barogo.dto.DeliveryDto;
import com.jiwon.example.barogo.entity.QDelivery;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DeliveryRepositoryCustomImpl implements DeliveryRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<DeliveryDto> findDeliveryList(Integer startDate, Integer endDate, int status) {

        QDelivery delivery = QDelivery.delivery;

        BooleanBuilder builder = new BooleanBuilder();

        if(null != startDate && null != endDate) {
            builder.and(delivery.orderDate.between(startDate, endDate));
        }
        if(0 != status) {
            builder.and(delivery.status.eq(status));
        }

        return queryFactory
                .select(Projections.fields(DeliveryDto.class,
                        delivery.id, delivery.orderDate, delivery.status,
                        delivery.address, delivery.addressDetail,
                        delivery.createdAt, delivery.updatedAt))
                .from(delivery)
                .where(builder)
                .orderBy(delivery.id.asc())
                .fetch();
    }

}
