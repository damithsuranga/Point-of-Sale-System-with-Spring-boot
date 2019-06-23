package lk.ijse.dep.sping.finalproject.repositary;

import lk.ijse.dep.sping.finalproject.entity.Item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepositary extends JpaRepository<Item,String> {
    
}
