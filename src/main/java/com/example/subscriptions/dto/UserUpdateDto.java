package com.example.subscriptions.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserUpdateDto {

    @Email(message = "Некорректный формат email")
    private String email;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "Дата рождения должна быть в формате ГГГГ-ММ-ДД")
    private String birthday;

    @Size(min = 2, max = 25)
    private String firstName;

    @Size(min = 2, max = 25)
    private String lastName;

    @Pattern(regexp = "\\+?\\d{10,15}")
    private String phone;

    private String info;

    public UserUpdateDto() {
    }

    public UserUpdateDto(String email, String birthday, String firstName,
                         String lastName, String phone, String info) {
        this.email = email;
        this.birthday = birthday;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.info = info;
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
