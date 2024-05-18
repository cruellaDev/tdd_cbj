package com.study.tdd.chapters.chapter03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryCalculator {

    public LocalDate calculateExpiryDate(PayData payData) {
        int A_YEAR_PACK_AMOUNT = 100_000;
        int A_YEAR_PACK_DURATION = 12;
        int addedMonths = payData.getPayAmount() == A_YEAR_PACK_AMOUNT
                ? A_YEAR_PACK_DURATION
                : payData.getPayAmount() / 10_000;
        if (payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonths);
        }
        return payData.getBillingDate().plusMonths(addedMonths);
    }

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
        if (isSameDayOfMonth(payData.getFirstBillingDate(), candidateExp)) {
            return candidateExp; // 교재 오타일까..?
        }
        final int dayLenOfCandiMon = lastDayOfMonth(candidateExp);
        final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
        if (dayLenOfCandiMon < dayOfFirstBilling) {
            return candidateExp.withDayOfMonth(dayLenOfCandiMon);
        }
        return candidateExp.withDayOfMonth(dayOfFirstBilling);

    }

    private boolean isSameDayOfMonth(LocalDate date1, LocalDate date2) {
        return date1.getDayOfMonth() == date2.getDayOfMonth();
    }

    private int lastDayOfMonth(LocalDate date) {
        return YearMonth.from(date).lengthOfMonth();
    }
}
