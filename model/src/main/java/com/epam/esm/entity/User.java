package com.epam.esm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Entity of an user.
 *
 * @author Dzmitry Ahinski
 */
@Entity(name = "users")
@Table(name = "users")
public class User extends RepresentationModel<Tag> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    @Column(name = "user_name")
    private String name;
    private BigDecimal cash;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Order> orders;

    public User() {
    }

    public User(String name, BigDecimal cash) {
        this.name = name;
        this.cash = cash;
    }

    public User(long id, String name, BigDecimal cash, Set<Order> orders) {
        this.id = id;
        this.name = name;
        this.cash = cash;
        this.orders = orders;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (cash != null ? !cash.equals(user.cash) : user.cash != null) return false;
        return orders != null ? orders.equals(user.orders) : user.orders == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (cash != null ? cash.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", cash=").append(cash);
        sb.append('}');
        return sb.toString();
    }
}
