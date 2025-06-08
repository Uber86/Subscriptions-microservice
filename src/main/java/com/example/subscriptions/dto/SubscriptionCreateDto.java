package com.example.subscriptions.dto;

import jakarta.validation.constraints.NotBlank;

public class SubscriptionCreateDto {

    @NotBlank(message = "Название сервиса обязательно")
    private String serviceName;

    public SubscriptionCreateDto(String serviceName) {
        this.serviceName = serviceName;
    }

    public SubscriptionCreateDto() {
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
