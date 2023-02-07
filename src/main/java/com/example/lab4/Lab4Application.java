package com.example.lab4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Lab4Application {
    private static final Logger log = LoggerFactory.getLogger(Lab4Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Lab4Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(AddressBookRepository addressBookRepository) {
        return (args) -> {
            log.info("--------------------");

            AddressBook book = new AddressBook();
            book.addBuddyInfo(new BuddyInfo("Bruce", "Home"));
            book.addBuddyInfo(new BuddyInfo("Tommy", "Home"));
            addressBookRepository.save(book);

            AddressBook book2 = new AddressBook();
            book2.addBuddyInfo(new BuddyInfo("Jimmy2", "Home2"));
            book2.addBuddyInfo(new BuddyInfo("Tommy2", "Home2"));
            addressBookRepository.save(book2);
            log.info("Get buddyinfo from addressBook");

            for (AddressBook mybook : addressBookRepository.findAll()) {
                List<BuddyInfo> myinfos = mybook.getBuddyList();
                for (BuddyInfo buddy : myinfos) {
                    System.out.println("Name: " + buddy.getName() +" Address: " + buddy.getAddress());

                }
            }
        };
    }
}
