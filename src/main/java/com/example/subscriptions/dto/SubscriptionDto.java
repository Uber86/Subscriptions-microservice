package com.example.subscriptions.dto;

import java.time.LocalDateTime;

public class SubscriptionDto {

    private Long id;
    private String serviceName;
    private String status;
    private LocalDateTime dataStart;
    private LocalDateTime dataEnd;

    public SubscriptionDto() {
    }

    public SubscriptionDto(Long id, String serviceName,
                           String status, LocalDateTime dataStart,
                           LocalDateTime dataEnd) {
        this.id = id;
        this.serviceName = serviceName;
        this.status = status;
        this.dataStart = dataStart;
        this.dataEnd = dataEnd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataStart() {
        return dataStart;
    }

    public void setDataStart(LocalDateTime dataStart) {
        this.dataStart = dataStart;
    }

    public LocalDateTime getDataEnd() {
        return dataEnd;
    }

    public void setDataEnd(LocalDateTime dataEnd) {
        this.dataEnd = dataEnd;
    }
}
