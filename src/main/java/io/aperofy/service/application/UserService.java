package io.aperofy.service.application;

import io.aperofy.entities.application.EventEntity;

public interface UserService {
    void addEventToUser(Long userId, EventEntity newEvent);
}
