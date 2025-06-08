package com.example.subscriptions.dto;

import jakarta.validation.constraints.*;

public class UserCreateDto {

    @Email(message = "Некорректный формат email")
    @NotBlank(message = "Email обязателен")
    private String email;

    @PastOrPresent(message = "Дата рождения не может быть в будущем")
    @NotBlank(message = "Дата рождения обязательна")
    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}",
            message = "Дата рождения должна быть в формате ДД-ММ-ГГГГ")
    private String birthday;

    @NotBlank(message = "Имя обязательно")
    @Size(min = 2, max = 25, message = "Имя должно быть от 2 до 25 символов")
    private String firstName;

    @NotBlank(message = "Фамилия обязательна")
    @Size(min = 2, max = 25, message = "Фамилия должна быть от 2 до 25 символов")
    private String lastName;

    @NotBlank(message = "Телефон обязателен")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}",
            message = "Неверный формат телефона")
    private String phone;

    public UserCreateDto() {
    }

    public UserCreateDto(String email,
                         String birthday,
                         String firstName,
                         String lastName,
                         String phone) {
        this.email = email;
        this.birthday = birthday;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
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
}
