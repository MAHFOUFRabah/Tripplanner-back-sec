package io.aperofy.entities.application;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class EventEntity {
    @Id
    @Column(name = "id_voyage")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvent;

    @Column(name = "title_event")
    private String titleEvent;

    @Column(name = "event_code")
    private String eventCode;


    @JsonManagedReference
    @OneToMany(mappedBy = "eventEntity", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<ItemEntity> items = new HashSet<>();

    public EventEntity() {
        super();
    }

    public EventEntity(String titleEvent, String eventCode) {
        super();
        this.titleEvent = titleEvent;
        this.eventCode = eventCode;
    }

    public Long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Long idEvent) {
        this.idEvent = idEvent;
    }

    public String getTitleEvent() {
        return this.titleEvent;
    }

    public void setTitleEvent(String titleEvent) {
        this.titleEvent = titleEvent;
    }


    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }


    public Set<ItemEntity> getItems() {
        return items;
    }

    public void setItems(Set<ItemEntity> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "EventEntity{" +
                "idEvent=" + idEvent +
                ", titleEvent='" + titleEvent + '\'' +
                ", eventCode='" + eventCode + '\'' +
                ", items=" + items +
                '}';
    }
}
