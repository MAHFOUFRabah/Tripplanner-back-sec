package io.aperofy.web.application;

import io.aperofy.entities.application.EventEntity;
import io.aperofy.service.application.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class EventController {
    @Autowired
    EventService eventService;
    @CrossOrigin
    @RequestMapping(value = "/allEvent/{username}", method = RequestMethod.GET)
    public Set<EventEntity> fetchALlEventsOfUser(@PathVariable String username) {

        return eventService.fetchALlEventsOfUser(username);
    }

    @CrossOrigin
    @RequestMapping(value = "/oneEvent/{idEvent}", method = RequestMethod.GET)
    public EventEntity fetchEventById(@PathVariable Long idEvent) {
        return eventService.fetchEventById(idEvent);
    }
    @CrossOrigin
    @RequestMapping(value = "/oneEvent/{username}", method = RequestMethod.POST)
    public void addNewEvent(@RequestBody EventEntity event,@PathVariable String username) {

         eventService.addNewEvent(event, username);
    }
    @CrossOrigin
    @RequestMapping(value = "/oneevent/{username}/{idVoyage}", method = RequestMethod.DELETE)
    public void deleteEventById(@PathVariable String username, @PathVariable Long idEvent) {
        eventService.deleteEventById(username, idEvent);
    }

    @CrossOrigin
    @RequestMapping(value = "/oneTrip/{id}/{nouveauNomDuVoyage}", method = RequestMethod.PUT)
    public EventEntity updateEvent(@PathVariable Long id, @RequestBody EventEntity eventEntity) {

        return eventService.updateEvent(id, eventEntity);

    }




}
