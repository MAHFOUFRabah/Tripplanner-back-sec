package io.aperofy.service.application.impl;

import io.aperofy.dao.application.ItemRepository;
import io.aperofy.entities.application.EventEntity;
import io.aperofy.entities.application.ItemEntity;
import io.aperofy.service.application.EventService;
import io.aperofy.service.application.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    EventService eventService;

    @Override
    public Set<ItemEntity> fetchAllItemOfEvent(Long idEvent) {
        EventEntity eventFetched = eventService.fetchEventById(idEvent);
        return eventFetched.getItems();

    }

    @Override
    public ItemEntity fetchItemById(Long idItem) {
        Optional itemFetched = itemRepository.findById(idItem);
        if (itemFetched.isPresent()) {
            return (ItemEntity) itemFetched.get();
        } else {
            throw new RuntimeException("No item was found");
        }

    }

    @Override
    public ItemEntity addItemToEvent(ItemEntity itemToAdd, Long idEvent) {
        EventEntity eventEntity = eventService.fetchEventById(idEvent);
        itemToAdd.setEventEntity(eventEntity);
        return itemRepository.save(itemToAdd);

    }

    @Override
    public void deleteItemOfEvent(Long idItemToDelete) {
        itemRepository.deleteById(idItemToDelete);
        boolean isStillExist = itemRepository.existsById(idItemToDelete);
        if (isStillExist) {
            throw new RuntimeException("The item wasn't deleted well !");
        }

    }


    @Override
    public ItemEntity addItemToMyTodoList(Long idItemToAdd, String username) {
        ItemEntity itemEntity = fetchItemById(idItemToAdd);
        itemEntity.setItemOwner(username);
        return itemRepository.save(itemEntity);
    }
}
