package lk.ijse.dep.sping.finalproject.controller;

import lk.ijse.dep.sping.finalproject.dto.OrderDTO;
import lk.ijse.dep.sping.finalproject.dto.OrderDetailDTO;
import lk.ijse.dep.sping.finalproject.service.custom.CustomerService;
import lk.ijse.dep.sping.finalproject.service.custom.ItemService;
import lk.ijse.dep.sping.finalproject.service.custom.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RequestMapping("api/v1/orders")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ItemService itemService;

//    public List<OrderDTO> getAllOrders(){
//        return orderService
//    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> placeOrder(@RequestBody OrderDTO orderDTO){
         if(orderDTO.getCustomerId().isEmpty()||orderDTO.getOrderDetails().size()<=0){
             return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
         }else {
             if(orderService.isItemExists(orderDTO.getOrderId())){
                 throw new RuntimeException("Order is already exist");
             }else {
                 orderService.placeOrder(orderDTO);
                 return new ResponseEntity<Void>(HttpStatus.CREATED);
             }
         }
    }

    @GetMapping
    public List<OrderDTO> getAllOrders(){
        return orderService.getAllOrders();
    }
}
