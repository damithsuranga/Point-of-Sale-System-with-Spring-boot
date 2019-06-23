package lk.ijse.dep.sping.finalproject.controller;

import lk.ijse.dep.sping.finalproject.dto.CustomerDTO;
import lk.ijse.dep.sping.finalproject.service.custom.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RequestMapping("api/v1/customers")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> getAllCustomer()  {
        return customerService.getAllCustomers();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveCustomer(@RequestBody CustomerDTO customerDTO){
        if(customerDTO.getId().isEmpty()||customerDTO.getName().isEmpty()||customerDTO.getAddress().isEmpty()){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }else{
            String customerID = customerService.saveCustomer(customerDTO);
            return new ResponseEntity<String>(customerID,HttpStatus.CREATED);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerDTO>> getAllCustomersCount(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Count",customerService.getAllCustomersCount()+"");
        httpHeaders.setAccessControlAllowHeaders(Arrays.asList("X-Count"));
        httpHeaders.setAccessControlExposeHeaders(Arrays.asList("X-Count"));
        return new ResponseEntity<List<CustomerDTO>>(customerService.getAllCustomersCount(),HttpStatus.OK);
    }


    @GetMapping(value = "/{id:C\\d{3}}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("id") String id){
        CustomerDTO dto = null;
        if(customerService.isCustomerExists(id)){
            dto = customerService.getCustomerById(id);
        }

        return new ResponseEntity<CustomerDTO>(dto,(dto!=null)?HttpStatus.OK:HttpStatus.NOT_FOUND);
    }

    @PutMapping(path = "/{id:C\\d{3}}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCustomer(@PathVariable("id")String id,@RequestBody CustomerDTO customerDTO){
        if(customerService.isCustomerExists(id)){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        if(customerDTO.getName().isEmpty()||customerDTO.getAddress().isEmpty()){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }else {
            customerDTO.setId(id);
            customerService.updateCustomer(customerDTO);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping(path = "/{id:C\\d{3}}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") String id){
        if(!customerService.isCustomerExists(id)){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        customerService.removeCustomer(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


}
