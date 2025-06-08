package com.example.subscriptions.dto;

public class UserDto {
    private Long id;
    private String email;
    private String birthday;
    private String firstName;
    private String lastName;
    private String phone;
    private String info;


    public UserDto() {
    }

    public UserDto(Long id, String email, String birthday,
                   String firstName, String lastName,
                   String phone, String info) {
        this.id = id;
        this.email = email;
        this.birthday = birthday;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
