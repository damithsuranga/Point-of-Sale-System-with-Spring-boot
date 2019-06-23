package lk.ijse.dep.sping.finalproject.controller;

import lk.ijse.dep.sping.finalproject.dto.ItemDTO;
import lk.ijse.dep.sping.finalproject.service.custom.ItemService;
import org.omg.CORBA.portable.ValueOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("api/v1/items")
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<ItemDTO> getAllItem(){
        return itemService.getAllItems();
    }

    @GetMapping(value = "/{code:I\\d{3}}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemDTO> getItem(@PathVariable("code")String code){
        ItemDTO dto = null;
        if(itemService.isItemExists(code)){
            dto = itemService.getItemBycode(code);
        }

        return new ResponseEntity<ItemDTO>(dto,(dto!=null)?HttpStatus.OK:HttpStatus.BAD_REQUEST);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveItem(@RequestBody ItemDTO itemDTO){
        if(itemDTO.getCode().isEmpty()||itemDTO.getDescription().isEmpty()){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        if(itemDTO.getQtyOnHand()==0||itemDTO.getUnitPrice()==0){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }else {
            String ItemCode = itemService.saveItem(itemDTO);
            return new ResponseEntity<String>(ItemCode,HttpStatus.CREATED);
        }
    }

    @PutMapping(path = "/{code:I\\{3}}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateItem(@PathVariable("code") String code,ItemDTO itemDTO){
        if(itemService.isItemExists(code)){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        if (itemDTO.getDescription().isEmpty()){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        if(itemDTO.getUnitPrice()==0||itemDTO.getQtyOnHand()==0){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }else{
            itemDTO.setCode(code);
            itemService.updateItem(itemDTO);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping(path = "/{code:I\\d{3}}")
    public ResponseEntity<Void> deleteItem(@PathVariable("code") String code){
        if(!itemService.isItemExists(code)){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
            itemService.deleteItem(code);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

    }

}
