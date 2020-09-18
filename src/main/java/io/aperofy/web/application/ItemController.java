package io.aperofy.web.application;

import io.aperofy.entities.application.ItemEntity;
import io.aperofy.service.application.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class ItemController {
@Autowired
    ItemService itemService;
    @CrossOrigin
    @RequestMapping(value = "/allItems/getAllItems/{idEvent}", method = RequestMethod.GET)
    public Set<ItemEntity> fetchAllItemOfEvent(@PathVariable Long idEvent) {

        return itemService.fetchAllItemOfEvent(idEvent);
    }
    @CrossOrigin
    @RequestMapping(value = "/oneItem/getItemById/{idItem}", method = RequestMethod.GET)
    public ItemEntity fetchItemById(@PathVariable Long idItem) {
        return itemService.fetchItemById(idItem); 
    }

    @CrossOrigin
    @RequestMapping(value = "/oneItem/addNewItem/{idEvent}", method = RequestMethod.POST)
    public ItemEntity addItemToEvent(@RequestBody ItemEntity itemEntity, @PathVariable Long idEvent) {
        return itemService.addItemToEvent(itemEntity, idEvent);
    }
    @CrossOrigin
    @RequestMapping(value = "/oneItem/deleteOnItem/{idItem}", method = RequestMethod.DELETE)
    public void deleteItemOfEvent(@PathVariable Long idItem) {
        itemService.deleteItemOfEvent(idItem);
    }

    @CrossOrigin
    @RequestMapping(value = "/oneItem/addItemToTODO/{username}/{idItem}", method = RequestMethod.POST)
    public ItemEntity addItemToMyTodoList( @PathVariable Long idItem, @PathVariable String username) {
        return itemService.addItemToMyTodoList(idItem, username);
    }

}
