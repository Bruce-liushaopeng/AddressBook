package com.example.lab4;

public class IdObj{
    private long AddressBookId;
    private long buddyInfoId;

    public IdObj() {

    }

    public long getAddressBookId() {
        return AddressBookId;
    }

    public void setAddressBookId(long addressBookId) {
        AddressBookId = addressBookId;
    }

    public long getBuddyInfoId() {
        return buddyInfoId;
    }

    public void setBuddyInfoId(long buddyInfoId) {
        this.buddyInfoId = buddyInfoId;
    }
}
