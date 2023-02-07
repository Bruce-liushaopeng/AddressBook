package com.example.lab4;
import jakarta.persistence.*;
import java.util.*;

@Entity
public class AddressBook {
    @Id
    @GeneratedValue
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BuddyInfo> buddyList;

    public AddressBook() {
        this.buddyList = new ArrayList<>();
    }

    public List<BuddyInfo> getBuddyList() {
        return buddyList;
    }

    public void setBuddyList(List<BuddyInfo> buddyList) {
        this.buddyList = buddyList;
    }

    public void addBuddyInfo(BuddyInfo b) {
        this.buddyList.add(b);
    }

    public void removeBuddyInfo(int index) {
        this.buddyList.remove(index);
    }
}
