package com.example.lab4;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddressBookControllerTest {
    @Autowired

    private AddressBookController addressBookController;
    @Value(value="${local.server.port}")
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception {
        assertThat(addressBookController).isNotNull();
    }

    @Test
    void getBuddyByIDTest() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/getBuddyById?addressBookId=1&buddyInfoId=1",
                String.class)).contains("Bruce");
    }

    @Test
    void addNewBuddyInfoTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        BuddyInfo newInfo = new BuddyInfo("Charles", "Carleton");

        HttpEntity<BuddyInfo> requestEntity = new HttpEntity<>(newInfo, headers);
        this.restTemplate.getForObject("http://localhost:" + port + "/getBuddyById?addressBookId=1&buddyInfoId=1", String.class);
        String response = this.restTemplate.postForObject("http://localhost:" + port + "/addBuddyInfoToAddressBook?id=1", requestEntity, String.class);
        assertThat(response).contains("Charles");
    }

    @Test
    void removeBuddyInfoTest(){
        this.restTemplate.delete("http://localhost:" + port + "/deleteBuddyById?addressBookId=1&buddyInfoId=1");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/getAllBuddyFromAddressBookById?Id=1",
                String.class)).doesNotContain("Bruce");
    }
}