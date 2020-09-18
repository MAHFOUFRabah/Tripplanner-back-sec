package io.aperofy.service.application.impl;

import io.aperofy.dao.application.EventRepository;
import io.aperofy.dao.security.UserRepository;
import io.aperofy.entities.application.EventEntity;
import io.aperofy.entities.security.AppUser;
import io.aperofy.service.application.EventService;
import io.aperofy.utils.FunctionsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class EventServiceImpl implements EventService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    FunctionsUtils functionsUtils;

    @Override
    public Set<EventEntity> fetchALlEventsOfUser(String username) {
        AppUser appUser = getAppUserFromDb(username);
        Set<EventEntity> allEventOfTheUser = appUser.getEventEntities();
        allEventOfTheUser.forEach(event -> event.getItems().clear());
        return  allEventOfTheUser;

    }

    @Override
    public EventEntity fetchEventById(Long idEvent) {
        Optional eventEntityOptional = eventRepository.findById(idEvent);
        if (eventEntityOptional.isPresent()) {
            return (EventEntity) eventEntityOptional.get();
        } else {
            throw new RuntimeException("No event was found !");
        }
    }

    @Override
    public EventEntity addNewEvent(EventEntity eventToAdd, String username) {
        AppUser user = getAppUserFromDb(username);
        String eventCode = functionsUtils.generateEventCode();
        eventToAdd.setEventCode(eventCode);
        user.getEventEntities().add(eventToAdd);
         userRepository.save(user);
        return  eventRepository.findByEventCode(eventCode);


    }


    @Override
    public void deleteEventById(String username, Long idEvent) {
        AppUser appUser = getAppUserFromDb(username);
        Set<EventEntity> eventsToBeModyfied = appUser.getEventEntities();
        eventsToBeModyfied.removeIf(event -> event.getIdEvent().equals(idEvent));

        appUser.setEventEntities(eventsToBeModyfied);
        userRepository.save(appUser);

    }

    @Override
    public EventEntity updateEvent(Long idOldEvent, EventEntity updatedEvent) {
        EventEntity oldEvent = fetchEventById(idOldEvent);
        oldEvent.setTitleEvent(updatedEvent.getTitleEvent());
        return eventRepository.save(oldEvent);
    }

    private AppUser getAppUserFromDb(String username) {
        AppUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("No user was found !");
        } else {
            return user;
        }

    }


}
