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
    @RequestMapping(value = "/allItems/{idEvent}", method = RequestMethod.GET)
    public Set<ItemEntity> recupererTousLesArticlesDuVoyageWS(@PathVariable Long idEvent) {

        return itemService.fetchAllItemOfEvent(idEvent);
    }
    @CrossOrigin
    @RequestMapping(value = "/oneItem/{idItem}", method = RequestMethod.GET)
    public ItemEntity recupererUnArticleApartirDeSonIdWS(@PathVariable Long idItem) {
        return itemService.fetchItemById(idItem); 
    }

    @CrossOrigin
    @RequestMapping(value = "/oneItem/{idEvent}", method = RequestMethod.POST)
    public ItemEntity ajouterUnArticleDuVoyage(@RequestBody ItemEntity itemEntity, @PathVariable Long idEvent) {
        return itemService.addItemToEvent(itemEntity, idEvent);
    }
    @CrossOrigin
    @RequestMapping(value = "/oneItem/{idItem}", method = RequestMethod.DELETE)
    public void supprimerUnArticleApartDeSonIdWS(@PathVariable Long idItem) {
        itemService.deleteItemOfEvent(idItem);
    }

    @CrossOrigin
    @RequestMapping(value = "/oneItem/{username}/{idItem}", method = RequestMethod.POST)
    public ItemEntity ajouterUnArticleDuVoyage( @PathVariable Long idItem, @PathVariable String username) {
        return itemService.addItemToMyTodoList(idItem, username);
    }

}
