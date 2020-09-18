package io.aperofy.utils.Impl;

import io.aperofy.dao.application.EventRepository;
import io.aperofy.entities.application.EventEntity;
import io.aperofy.utils.FunctionsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FunctionsUtilsImpl implements FunctionsUtils {
    @Autowired
    EventRepository eventRepository;

    @Override
    public String generateEventCode() {
        String generatedString = generateRandomString();
        EventEntity eventData = this.eventRepository.findByEventCode(generatedString);
        if (eventData != null) {
            generateEventCode();
        }
        return generatedString;

    }

    private String generateRandomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        generatedString = generatedString.toUpperCase();

        return generatedString;
    }
}
