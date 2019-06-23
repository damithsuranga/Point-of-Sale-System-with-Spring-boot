package lk.ijse.dep.sping.finalproject.repositary;

import lk.ijse.dep.sping.finalproject.entity.OrderDetail;
import lk.ijse.dep.sping.finalproject.entity.OrderDetailPK;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepositary extends JpaRepository<OrderDetail, OrderDetailPK> {
    
}
