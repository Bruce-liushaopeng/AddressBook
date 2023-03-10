package com.example.lab4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.google.gson.Gson;

@RestController
public class SPAController {

    @Autowired
    private AddressBookRepository repo;
    @GetMapping("/spaGetAllBuddyFromAddressBookById")
    public String spaGetAllBuddyFromAddressBookById(@RequestParam(value="id", defaultValue = "1") String id, Model model) {
        Optional<AddressBook> bookOptional = repo.findById(Long.parseLong(id));
        AddressBook book = bookOptional.get();
        List<BuddyInfo> buddyInfos = book.getBuddyList();
        String res = new Gson().toJson(buddyInfos);
        model.addAttribute("buddyInfos", buddyInfos);
        return res;
    }

    @GetMapping("/spaGetBuddyById")
    public String spaGetBuddyByID(
            @RequestParam(value="addressBookId", defaultValue = "0") String addressBookId,
            @RequestParam(value="buddyInfoId", defaultValue = "0") String buddyInfoId,
            Model model) {
        Optional<AddressBook> bookOptional = repo.findById(Long.parseLong(addressBookId));
        AddressBook book = bookOptional.get();
        List<BuddyInfo> buddyInfos = book.getBuddyList();
        BuddyInfo requestedBuddyInfo = buddyInfos.stream()
                .filter(info -> info.getId().equals(Long.parseLong(buddyInfoId)))
                .findAny()
                .orElse(null);
        model.addAttribute("buddyInfos", requestedBuddyInfo);
        String res = new Gson().toJson(requestedBuddyInfo);
        return res;
    }
//
//    @DeleteMapping("/deleteBuddyById")
//    public String deleteBuddyById(@RequestParam(value="addressBookId", defaultValue = "0") String addressBookId,
//                                  @RequestParam(value="buddyInfoId", defaultValue = "0") String buddyInfoId,
//                                  Model model) {
//        List<AddressBook> books = new ArrayList<>();
//        Optional<AddressBook> bookOptional = repo.findById(Long.parseLong(addressBookId));
//        AddressBook book = bookOptional.get();
//        List<BuddyInfo> buddyInfos = book.getBuddyList();
//        BuddyInfo targetBuddy = buddyInfos.stream()
//                .filter(info -> info.getId().equals(Long.parseLong(buddyInfoId)))
//                .findAny()
//                .orElse(null);
//        buddyInfos.remove(targetBuddy);
//        List<BuddyInfo> deletedBuddyInfo = new ArrayList<>();
//        deletedBuddyInfo.add(targetBuddy);
//        repo.save(book);
//        model.addAttribute("buddyInfos", deletedBuddyInfo);
//        return "buddyInfoPage";
//    }
//
//    @PostMapping(value="/addBuddyInfoToAddressBook",consumes = {"application/json"})
//    public String addBuddyInfo( @RequestParam(value="id", defaultValue = "1") String id, @RequestBody BuddyInfo newInfo, Model model) {
//        List<AddressBook> books = new ArrayList<>();
//        repo.findAll().forEach(books::add);
//        AddressBook book = books.get(Integer.parseInt(id) - 1);
//        book.addBuddyInfo(newInfo);
//        List<BuddyInfo> buddyInfos = book.getBuddyList();
//        repo.save(book);
//        model.addAttribute("buddyInfos", buddyInfos);
//        return "buddyInfoPage";
//    }
//
//    @PostMapping(value="/addAddressBook")
//    public String addAddressBook(Model model) {
//        AddressBook newBook = new AddressBook();
//        repo.save(newBook);
//        List<AddressBook> books = new ArrayList<>();
//        repo.findAll().forEach(books::add);
//        newBook = books.get(books.size()-1);
//        String newId = newBook.getId() + "";
//        model.addAttribute("id", newId);
//        return "addAddressBookSuccess";
//    }
//
//    @DeleteMapping(value="/deleteAddressBookById")
//    public String deleteAddressBookById(@RequestParam(value="id") String id, Model model) {
//        repo.deleteById(Long.parseLong(id));
//        model.addAttribute("id", id);
//        return "deleteAddressBookSuccess";
//    }
}