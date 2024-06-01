package com.study.tdd.chapters.chapter07;

import java.time.LocalDateTime;

public class AutoDebitInfo {

    private String userId;
    private String cardNumber;
    private LocalDateTime registeredDate;

    public AutoDebitInfo(String userId, String cardNumber, LocalDateTime dateTime) {
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.registeredDate = dateTime;
    };

    public String getUserId() {
        return userId;
    }

    public void changeCardNumber(String cardNumber) {
        //
    }
}
