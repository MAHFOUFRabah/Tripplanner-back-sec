package io.aperofy;

import io.aperofy.dao.security.UserRepository;
import io.aperofy.entities.application.EventEntity;
import io.aperofy.entities.application.ItemEntity;
import io.aperofy.entities.security.AppRole;
import io.aperofy.entities.security.AppUser;
import io.aperofy.service.application.EventService;
import io.aperofy.service.application.ItemService;
import io.aperofy.service.security.AccountService;
import io.aperofy.utils.FunctionsUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.Stream;

@SpringBootApplication
public class SecServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(SecServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AccountService accountService, FunctionsUtils functionsUtils, EventService eventService, UserRepository userRepository, ItemService itemService) {
        return args -> {


            accountService.saveRole(new AppRole(null, "USER"));
            accountService.saveRole(new AppRole(null, "ADMIN"));
            Stream.of("user1", "user2", "user3", "admin").forEach(un -> {
                AppUser appUser = accountService.saveUser(un, "1234", "1234");

            });
            accountService.addRoleToUser("admin", "ADMIN");

            String generatedCodeValue = functionsUtils.generateTripCode();
            EventEntity eventEntity = new EventEntity();
            eventEntity.setEventCode(generatedCodeValue);
            eventEntity.setTitleEvent("Bordeaux");




            eventService.addNewEvent(eventEntity, "admin");
            eventEntity.setIdEvent(new Long(1));
            eventService.addNewEvent(eventEntity, "user1");
            eventEntity.setTitleEvent("Paris");
            eventService.updateEvent(new Long(1), eventEntity);

            ItemEntity itemEntity = new ItemEntity();
            itemEntity.setTitleItem("Fromage");
            itemService.addItemToEvent(itemEntity, new Long(1));
            //eventService.deleteEventById("admin",eventEntity.getIdVoyage());
            AppUser userData = accountService.loadUserByUsername("admin");
            AppUser userData2 = accountService.loadUserByUsername("user1");
           // System.out.println(userData2);
            //System.out.println(userData);
            System.out.println("***********");
            System.out.println(itemService.fetchAllItemOfEvent(new Long(1)));
            System.out.println("***********");
            System.out.println(itemService.fetchItemById(new Long(1)));
            System.out.println("***********");
            System.out.println(itemService.addItemToMyTodoList(new Long(1),"admin"));

            /*userData.setEventEntities(eventsToAdd);
            userRepository.save(userData);

            userData = accountService.loadUserByUsername("user1");
            System.out.println(userData);*/


        };
    }


    @Bean
    BCryptPasswordEncoder getBCPE() {
        return new BCryptPasswordEncoder();
    }

}
