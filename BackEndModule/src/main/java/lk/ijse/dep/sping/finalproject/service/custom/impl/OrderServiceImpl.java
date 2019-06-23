package lk.ijse.dep.sping.finalproject.service.custom.impl;

import lk.ijse.dep.sping.finalproject.dto.CustomerDTO;
import lk.ijse.dep.sping.finalproject.dto.OrderDTO;
import lk.ijse.dep.sping.finalproject.dto.OrderDetailDTO;
import lk.ijse.dep.sping.finalproject.entity.Customer;
import lk.ijse.dep.sping.finalproject.entity.Item;
import lk.ijse.dep.sping.finalproject.entity.Order;
import lk.ijse.dep.sping.finalproject.entity.OrderDetail;
import lk.ijse.dep.sping.finalproject.repositary.CustomerRepositary;
import lk.ijse.dep.sping.finalproject.repositary.ItemRepositary;
import lk.ijse.dep.sping.finalproject.repositary.OrderDetailRepositary;
import lk.ijse.dep.sping.finalproject.repositary.OrderRepositary;
import lk.ijse.dep.sping.finalproject.service.custom.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepositary orderDAO;
    @Autowired
    private OrderDetailRepositary orderDetailDAO;
    @Autowired
    private ItemRepositary itemDAO;
    @Autowired
    private CustomerRepositary customerDAO;


    @Override
    public List<OrderDTO> getAllOrders() {
     List<OrderDTO> collect = orderDAO.findAll().stream().map(order -> new OrderDTO(order.getId(),order.getDate(),order.getCustomer().getId(),
     order.getOrderDetails().stream().map(orderDetail -> new OrderDetailDTO(orderDetail.getOrderDetailPK().getOrderId(),
             orderDetail.getOrderDetailPK().getItemCode(),orderDetail.getQty(),orderDetail.getUnitPrice())).collect(Collectors.toList()))).collect(Collectors.toList());
     return collect;

    }

    @Override
    public void placeOrder(OrderDTO order)  {


            Customer customer = customerDAO.getOne(order.getCustomerId());
        orderDAO.save(new Order(order.getOrderId(),order.getOrderDate(),customer));
            for(OrderDetailDTO dto : order.getOrderDetails()){
                orderDetailDAO.save(new OrderDetail(dto.getOrderId(),dto.getItemCode(),dto.getQty(),dto.getUnitPrice()));
                Item item = itemDAO.getOne(dto.getItemCode());
                int qty = item.getQtyOnHand()-dto.getQty();
                item.setQtyOnHand(qty);
            }


    }



    @Override
    public int generateOrderId()  {
        return orderDAO.getLastOrderId()+1;

    }

    @Override
    public boolean isItemExists(int OrderID) {
        return orderDAO.existsById(OrderID);
    }

    @Override
    public void updateItem(OrderDTO order) {
        //orderDAO.save(new Order(order.getOrderId(),order.getOrderDate(),order.getCustomerId()))
    }

    @Override
    public void deleteItem(int OrderID) {
            orderDAO.deleteById(OrderID);
    }
}
