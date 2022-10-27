package entities;

import dto.Order.AddOrdersDTO;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    private String userName;
    private Integer productId;
    private Integer quantity;


    public UserOrder(AddOrdersDTO dto) {
        userName = dto.getUser();
        productId = dto.getProductId();
        quantity = dto.getQuantity();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user='" + userName + '\'' +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}