package lk.ijse.dep.sping.finalproject.service.custom;

import lk.ijse.dep.sping.finalproject.dto.OrderDTO;
import lk.ijse.dep.sping.finalproject.service.SuperService;

import java.util.List;

public interface OrderService extends SuperService {

    public List<OrderDTO> getAllOrders();

    public void placeOrder(OrderDTO order) ;

    public int generateOrderId() ;

    boolean isItemExists(int OrderID);

    public void updateItem(OrderDTO order);

    public void deleteItem(int OrderID) ;

}
