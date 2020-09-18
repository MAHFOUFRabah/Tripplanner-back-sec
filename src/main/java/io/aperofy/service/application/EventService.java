package io.aperofy.service.application;


import io.aperofy.entities.application.EventEntity;

import java.util.Set;

public interface EventService {
    Set<EventEntity> fetchALlEventsOfUser(String username);

    EventEntity fetchEventById(Long idEvent);

    void addNewEvent(EventEntity eventToAdd, String username);

    void deleteEventById(String username, Long idVoyage);

    EventEntity updateEvent(Long idEvent, EventEntity upDatedEvent);


}
