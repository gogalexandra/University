package repository;

import entities.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public interface OrderRepository extends JpaRepository<UserOrder, Integer> {
    List<UserOrder> findAllByUserName(String userName);
}
