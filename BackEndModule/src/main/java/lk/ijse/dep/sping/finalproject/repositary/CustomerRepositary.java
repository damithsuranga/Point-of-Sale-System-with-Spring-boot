package lk.ijse.dep.sping.finalproject.repositary;

import lk.ijse.dep.sping.finalproject.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepositary extends JpaRepository<Customer,String> {

//    int countCustomerBy();
//
//    List<Customer> getTop5CustomerByOrderByNameAsc();
//
//    String getRandomCustomer();

//        Customer findFirstCustomerByOrderByAddressDesc();

        List<Customer> getAllCustomerByNameLike(String word);

//        @Query(value = "SELECT c FROM Customer c where c.name LIKE ?#{[0]}")
//        List<Customer>customList(String word);


    List<Customer> findCustomersByNameLikeAndAddressOrderByIdDesc(String name, String Address);


    @Query(value = "select c from Customer c WHERE c.name LIKE:#{#name + '%'} AND c.address LIKE:#{#address + '%'}")
    List<Customer> customerQ2(@Param("name") String name, @Param("address") String address);

}
