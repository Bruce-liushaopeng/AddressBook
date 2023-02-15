
$(document).ready(function () {
    $.ajax({
        url: "http://localhost:8080/spaGetAllBuddyFromAddressBookById?id=1"
    }).then(function (data) {
        if (data) {
            console.log(data)
            const jsonData = JSON.parse(data)
            for (i = 0; i < jsonData.length; i++) {
                $('.addressbook-contents').append("Name: " + jsonData[i].name + ", ").append("Address: " + jsonData[i].address).append("<br>");
            }
        }
    });
});