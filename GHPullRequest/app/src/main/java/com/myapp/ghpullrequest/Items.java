package com.myapp.ghpullrequest;

public class Items {

    private String number;
    private String status;
    private String created;
    private String title;

    public Items(String number, String status, String created, String title) {
        this.number = number;
        this.status = status;
        this.created = created;
        this.title = title;
    }

    public String getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public String getCreated() {
        return created;
    }
}
