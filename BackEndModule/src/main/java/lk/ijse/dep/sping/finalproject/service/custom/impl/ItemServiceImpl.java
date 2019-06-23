package lk.ijse.dep.sping.finalproject.service.custom.impl;

import lk.ijse.dep.sping.finalproject.dto.ItemDTO;
import lk.ijse.dep.sping.finalproject.entity.Item;
import lk.ijse.dep.sping.finalproject.repositary.ItemRepositary;
import lk.ijse.dep.sping.finalproject.service.custom.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepositary itemRepositary;

    public List<ItemDTO> getAllItems()  {


            List<ItemDTO> collect = itemRepositary.findAll().stream().map(item -> new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand())).collect(Collectors.toList());

            return collect;

        //        itemRepositary.findAll().stream().map(new Function<Item, ItemDTO>() {
//            @Override
//            public ItemDTO apply(Item item) {
//                return new ItemDTO(item.getCode(), item.getDescription(),item.getUnitPrice(), item.getQtyOnHand());
//            }
//        }).collect(Collectors.toList());

    }

    public String saveItem(ItemDTO item)  {

            return itemRepositary.save(new Item(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand())).getCode();


    }

    public void updateItem(ItemDTO item)  {

            itemRepositary.save(new Item(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));

    }

    public void deleteItem(String code) {

            itemRepositary.deleteById(code);

    }

    @Override
    public boolean isItemExists(String code) {
        return itemRepositary.existsById(code);
    }

    @Override
    public ItemDTO getItemBycode(String code) {
        Item item = itemRepositary.getOne(code);
        ItemDTO itemDTO = new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand());
        return itemDTO;
    }


}
