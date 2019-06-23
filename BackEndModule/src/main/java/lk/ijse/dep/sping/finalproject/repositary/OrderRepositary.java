package lk.ijse.dep.sping.finalproject.repositary;

import lk.ijse.dep.sping.finalproject.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepositary extends JpaRepository<Order,Integer> {

    @Query(value = "SELECT o.id FROM Orders o ORDER BY o.id DESC LIMIT 1",nativeQuery = true)
    int getLastOrderId();
}
