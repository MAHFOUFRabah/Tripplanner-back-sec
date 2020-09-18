package io.aperofy.service.application;


import io.aperofy.entities.application.ItemEntity;

import java.util.Set;

public interface ItemService {

    Set<ItemEntity> fetchAllItemOfEvent(Long idEvent);

    ItemEntity fetchItemById(Long idItem);

    ItemEntity addItemToEvent(ItemEntity itemToAdd, Long idEvent);

    void deleteItemOfEvent(Long IdItemToDelete);

    ItemEntity addItemToMyTodoList(Long idItemToAdd, String username);
}
