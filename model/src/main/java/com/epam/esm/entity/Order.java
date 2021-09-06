package com.epam.esm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity of an order.
 *
 * @author Dzmitry Ahinski
 */
@Entity(name = "orders")
@Table(name = "orders")
public class Order extends RepresentationModel<Order> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long id;

    private BigDecimal purchasePrice;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime purchaseTime;

    @ManyToOne
    @JoinColumn(name = "users_id_fk")
    User user;

    @ManyToOne
    @JoinColumn(name = "gift_certificates_id_fk")
    GiftCertificate certificate;

    public Order() {
    }

    public Order(long id, BigDecimal purchasePrice) {
        this.id = id;
        this.purchasePrice = purchasePrice;
    }

    public Order(BigDecimal purchasePrice, User user, GiftCertificate certificate) {
        this.purchasePrice = purchasePrice;
        this.user = user;
        this.certificate = certificate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalDateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GiftCertificate getCertificate() {
        return certificate;
    }

    public void setCertificate(GiftCertificate certificate) {
        this.certificate = certificate;
    }

    @PrePersist
    private void onPrePersist() {
        setPurchaseTime(LocalDateTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (purchasePrice != null ? !purchasePrice.equals(order.purchasePrice) : order.purchasePrice != null)
            return false;
        if (purchaseTime != null ? !purchaseTime.equals(order.purchaseTime) : order.purchaseTime != null) return false;
        if (user != null ? !user.equals(order.user) : order.user != null) return false;
        return certificate != null ? certificate.equals(order.certificate) : order.certificate == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (purchasePrice != null ? purchasePrice.hashCode() : 0);
        result = 31 * result + (purchaseTime != null ? purchaseTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", purchasePrice=").append(purchasePrice);
        sb.append(", purchaseTime=").append(purchaseTime);
        sb.append('}');
        return sb.toString();
    }
}
