package lk.ijse.dep.sping.finalproject.service.custom.impl;

import lk.ijse.dep.sping.finalproject.dto.CustomerDTO;
import lk.ijse.dep.sping.finalproject.entity.Customer;
import lk.ijse.dep.sping.finalproject.repositary.CustomerRepositary;
import lk.ijse.dep.sping.finalproject.service.custom.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    private CustomerRepositary customerRepositary;

    @Override
    public List<CustomerDTO> getAllCustomers()  {

            List<CustomerDTO> customerDTOStream = customerRepositary.findAll().stream().map(customer -> new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress())).collect(Collectors.toList());

            return customerDTOStream;

    }

    @Override
    public List<CustomerDTO> getAllCustomersCount() {
        List<CustomerDTO> customerDTOStreamCount = customerRepositary.findAll().stream().map(customer -> new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress())).collect(Collectors.toList());
        return customerDTOStreamCount;
    }

    @Override
    public String saveCustomer(CustomerDTO dto)  {

          return customerRepositary.save(new Customer(dto.getId(),dto.getName(),dto.getAddress())).getId();
    }

    @Override
    public void updateCustomer(CustomerDTO dto) {

            customerRepositary.save(new Customer(dto.getId(),dto.getName(),dto.getAddress()));

    }

    @Override
    public void removeCustomer(String id)  {

                customerRepositary.deleteById(id);

    }

    @Override
    public CustomerDTO getCustomerById(String id) {

            Customer customer = customerRepositary.getOne(id);
            CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress());

            return customerDTO;


    }

    @Override
    public boolean isCustomerExists(String id) {
        return customerRepositary.existsById(id);
    }



//    public CustomerServiceImpl(){
//        String dao = DAOFactory.getInstance().<String>getDAO(DAOTypes.CUSTOMER);
//    }

    //    public CustomerServiceImpl(){
//        String dao = DAOFactory.getInstance().<String>getDAO(DAOTypes.CUSTOMER);
//    }


}
