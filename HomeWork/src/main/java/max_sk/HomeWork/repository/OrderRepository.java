package max_sk.HomeWork.repository;

import max_sk.HomeWork.dto.OrderDTO;
import max_sk.HomeWork.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>  {
    @Query(value = " select FROM orders " +
            "inner join product on orders.id_product = product.id " +
            "inner join users on orders.id_user = users.id ", nativeQuery = true)
    List<Order> findAllOrderUsersNative();

}
