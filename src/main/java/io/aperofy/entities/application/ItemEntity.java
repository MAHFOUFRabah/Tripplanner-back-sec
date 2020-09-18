package io.aperofy.entities.application;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class ItemEntity {
    @Id
    @Column(name = "id_item")
    @GeneratedValue
    private Long idItem;

    @Column(name = "title_item")
    private String titleItem;
    @Column(name = "item_owner")
private String itemOwner;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ITEM")
    private EventEntity eventEntity;

    public ItemEntity(String titleItem, EventEntity eventEntity) {
        super();
        this.titleItem = titleItem;
        this.eventEntity = eventEntity;
    }


    public ItemEntity() {
        super();
    }


    public String getTitleItem() {
        return titleItem;
    }

    public void setTitleItem(String titleItem) {
        this.titleItem = titleItem;
    }

    public EventEntity getEventEntity() {
        return eventEntity;
    }

    public void setEventEntity(EventEntity eventEntity) {
        this.eventEntity = eventEntity;
    }

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public String getItemOwner() {
        return itemOwner;
    }

    public void setItemOwner(String itemOwner) {
        this.itemOwner = itemOwner;
    }

    @Override
    public String toString() {
        return "ItemEntity{" +
                "idItem=" + idItem +
                ", titleItem='" + titleItem + '\'' +
                '}';
    }
}
