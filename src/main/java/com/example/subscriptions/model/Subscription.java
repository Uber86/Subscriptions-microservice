package com.example.subscriptions.model;

import com.example.subscriptions.enums.SubscriptionStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Класс Subscription представляет подписки для пользователя
 */
@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="service_name")
    private String serviceName;

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;

    @Column(name = "data_start")
    private LocalDateTime dataStart;

    @Column(name = "data_end")
    private LocalDateTime dataEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Subscription() {
    }

    public Subscription(Long id, String serviceName,
                        SubscriptionStatus status,
                        LocalDateTime dataStart,
                        LocalDateTime dataEnd,
                        User user) {
        this.id = id;
        this.serviceName = serviceName;
        this.status = status;
        this.dataStart = dataStart;
        this.dataEnd = dataEnd;
        this.user = user;
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

    public SubscriptionStatus getStatus() {
        return status;
    }

    public void setStatus(SubscriptionStatus status) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return Objects.equals(id, that.id) && Objects.equals(serviceName, that.serviceName) && status == that.status && Objects.equals(dataStart, that.dataStart) && Objects.equals(dataEnd, that.dataEnd) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serviceName, status, dataStart, dataEnd, user);
    }
}
