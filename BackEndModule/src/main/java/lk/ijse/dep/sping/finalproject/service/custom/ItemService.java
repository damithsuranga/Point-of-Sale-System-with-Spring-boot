package lk.ijse.dep.sping.finalproject.service.custom;

import lk.ijse.dep.sping.finalproject.dto.ItemDTO;
import lk.ijse.dep.sping.finalproject.service.SuperService;


import java.util.List;

public interface ItemService extends SuperService {

    public List<ItemDTO> getAllItems() ;

    public String saveItem(ItemDTO item) ;

    public void updateItem(ItemDTO item);

    public void deleteItem(String code) ;

    boolean isItemExists(String code);

    public ItemDTO getItemBycode(String code);

}
