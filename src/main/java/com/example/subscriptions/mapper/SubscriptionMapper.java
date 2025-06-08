package com.example.subscriptions.mapper;

import com.example.subscriptions.dto.SubscriptionCreateDto;
import com.example.subscriptions.dto.SubscriptionDto;
import com.example.subscriptions.model.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    SubscriptionMapper INSTANCE = Mappers.getMapper(SubscriptionMapper.class);

    SubscriptionDto toDto(Subscription subscription);

    Subscription fromCreateDto(SubscriptionCreateDto dto);
}
