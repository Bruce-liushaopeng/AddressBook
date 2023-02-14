package com.example.lab4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AddressBookController {

    @Autowired
    private AddressBookRepository repo;
    @GetMapping("/getAllBuddyFromAddressBookById")
    public String getAllBuddyFromAddressBookById(@ModelAttribute("idObj") IdObj idObj, Model model) {
        System.out.println(idObj.getAddressBookId());
        Optional<AddressBook> bookOptional = repo.findById(idObj.getAddressBookId());
        AddressBook book = bookOptional.get();
        List<BuddyInfo> buddyInfos = book.getBuddyList();
        model.addAttribute("buddyInfos", buddyInfos);
        return "buddyInfoPage";
    }

    @GetMapping("/getBuddyById")
    public String getBuddyByID(@ModelAttribute("idObj") IdObj idObj, Model model) {
        Optional<AddressBook> bookOptional = repo.findById(idObj.getAddressBookId());
        AddressBook book = bookOptional.get();
        List<BuddyInfo> buddyInfos = book.getBuddyList();
        BuddyInfo requestedBuddyInfo = buddyInfos.stream()
                .filter(info -> info.getId().equals(idObj.getBuddyInfoId()))
                .findAny()
                .orElse(null);
        model.addAttribute("buddyInfos", requestedBuddyInfo);
        return "buddyInfoPage";
    }

    @DeleteMapping("/deleteBuddyById")
    public String deleteBuddyById(@RequestParam(value="addressBookId", defaultValue = "0") String addressBookId,
                                  @RequestParam(value="buddyInfoId", defaultValue = "0") String buddyInfoId,
                                  Model model) {
        List<AddressBook> books = new ArrayList<>();
        Optional<AddressBook> bookOptional = repo.findById(Long.parseLong(addressBookId));
        AddressBook book = bookOptional.get();
        List<BuddyInfo> buddyInfos = book.getBuddyList();
        BuddyInfo targetBuddy = buddyInfos.stream()
                .filter(info -> info.getId().equals(Long.parseLong(buddyInfoId)))
                .findAny()
                .orElse(null);
        buddyInfos.remove(targetBuddy);
        List<BuddyInfo> deletedBuddyInfo = new ArrayList<>();
        deletedBuddyInfo.add(targetBuddy);
        repo.save(book);
        model.addAttribute("buddyInfos", deletedBuddyInfo);
        return "buddyInfoPage";
    }

    @PostMapping(value="/addBuddyInfoToAddressBook",consumes = {"application/json"})
    public String addBuddyInfo( @RequestParam(value="id", defaultValue = "1") String id, @RequestBody BuddyInfo newInfo, Model model) {
        List<AddressBook> books = new ArrayList<>();
        repo.findAll().forEach(books::add);
        AddressBook book = books.get(Integer.parseInt(id) - 1);
        book.addBuddyInfo(newInfo);
        List<BuddyInfo> buddyInfos = book.getBuddyList();
        repo.save(book);
        model.addAttribute("buddyInfos", buddyInfos);
        return "buddyInfoPage";
    }

    @PostMapping(value="/addAddressBook")
    public String addAddressBook(Model model) {
        AddressBook newBook = new AddressBook();
        repo.save(newBook);
        List<AddressBook> books = new ArrayList<>();
        repo.findAll().forEach(books::add);
        newBook = books.get(books.size()-1);
        String newId = newBook.getId() + "";
        model.addAttribute("id", newId);
        return "addAddressBookSuccess";
    }

    @DeleteMapping(value="/deleteAddressBookById")
    public String deleteAddressBookById(@RequestParam(value="id") String id, Model model) {
        repo.deleteById(Long.parseLong(id));
        model.addAttribute("id", id);
        return "deleteAddressBookSuccess";
    }

    @GetMapping(value="/")
    public String homePage() {
        return "home";
    }

    @GetMapping(value="/viewAllBuddyInfoInAddressBookForm")
    public String getAddressBookIdForm(Model model) {
        model.addAttribute("idObj", new IdObj());
        return "viewAllBuddyInfoInAddressBookForm";
    }

    @GetMapping(value="/viewBuddyInfoForm")
    public String getBuddyInfoForm(Model model) {
        model.addAttribute("idObj", new IdObj());
        return "viewBuddyInfoForm";
    }
}
