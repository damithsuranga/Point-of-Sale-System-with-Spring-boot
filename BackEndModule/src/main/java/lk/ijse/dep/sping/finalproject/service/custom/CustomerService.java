package lk.ijse.dep.sping.finalproject.service.custom;

import lk.ijse.dep.sping.finalproject.dto.CustomerDTO;
import lk.ijse.dep.sping.finalproject.entity.Customer;
import lk.ijse.dep.sping.finalproject.service.SuperService;


import java.util.List;

public interface CustomerService extends SuperService {

    public List<CustomerDTO> getAllCustomers() ;

    String saveCustomer(CustomerDTO dto)  ;

    public void updateCustomer(CustomerDTO dto);

    public void removeCustomer(String id);

    public CustomerDTO getCustomerById(String id);

    boolean isCustomerExists(String id);

    public List<CustomerDTO> getAllCustomersCount();

}
