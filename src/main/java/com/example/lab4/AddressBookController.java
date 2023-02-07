package com.example.lab4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AddressBookController {

    @Autowired
    private AddressBookRepository repo;
    @GetMapping("/getAllBuddyFromAddressBookById")
    public String getAllBuddyFromAddressBookById(@RequestParam(value="id", defaultValue = "1") String id, Model model) {
        List<AddressBook> books = new ArrayList<>();
        repo.findAll().forEach(books::add);
        AddressBook book = books.get(Integer.parseInt(id) - 1);
        List<BuddyInfo> buddyInfos = book.getBuddyList();
        model.addAttribute("buddyInfos", buddyInfos);
        return "buddyInfoPage";
    }

    @GetMapping("/getBuddyById")
    public String getBuddyByID(@RequestParam(value="id", defaultValue = "0") String id ,Model model) {
        List<AddressBook> books = new ArrayList<>();
        repo.findAll().forEach(books::add);
        AddressBook book = books.get(0);
        List<BuddyInfo> buddyInfos = book.getBuddyList();
        List<BuddyInfo> requestedBuddyInfo = new ArrayList<>();
        requestedBuddyInfo.add(buddyInfos.get(Integer.parseInt(id) - 1));
        model.addAttribute("buddyInfos", requestedBuddyInfo);
        return "buddyInfoPage";
    }

    @DeleteMapping("/deleteBuddyById")
    public String deleteBuddyById(@RequestParam(value="id", required = true) String id, Model model) {
        List<AddressBook> books = new ArrayList<>();
        repo.findAll().forEach(books::add);
        AddressBook book = books.get(0);
        List<BuddyInfo> buddyInfos = book.getBuddyList();
        List<BuddyInfo> deletedBuddyInfo = new ArrayList<>();
        deletedBuddyInfo.add(buddyInfos.get(Integer.parseInt(id) - 1));
        book.removeBuddyInfo(Integer.parseInt(id) - 1);
        repo.save(book);
        model.addAttribute("buddyInfos", deletedBuddyInfo);
        return "buddyInfoPage";
    }

    @PostMapping(value="/addBuddyInfo",consumes = {"application/json"})
    public String addBuddyInfo(@RequestBody BuddyInfo newInfo, Model model) {
        List<AddressBook> books = new ArrayList<>();
        repo.findAll().forEach(books::add);
        AddressBook book = books.get(0);
        book.addBuddyInfo(newInfo);
        List<BuddyInfo> buddyInfos = book.getBuddyList();
        repo.save(book);
        model.addAttribute("buddyInfos", buddyInfos);
        return "buddyInfoPage";
    }

}
