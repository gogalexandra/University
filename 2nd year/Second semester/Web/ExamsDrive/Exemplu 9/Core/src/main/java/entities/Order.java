package entities;

import dtos.order.AddOrderDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String user;
    private Integer productId;
    private Integer quantity;

    public Order(AddOrderDTO dto) {
        user = dto.getUser();
        productId = dto.getProductId();
        quantity = dto.getQuantity();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", product=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}

