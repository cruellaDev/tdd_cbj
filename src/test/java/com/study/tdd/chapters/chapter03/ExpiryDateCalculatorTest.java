package com.study.tdd.chapters.chapter03;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ExpiryDateCalculatorTest {

    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        PayData pd1 = PayData
                .builder()
                .billingDate(LocalDate.of(2024, 1, 1))
                .payAmount(10_000)
                .build();
        asserExpiryDate(pd1, LocalDate.of(2024, 2, 1));
        PayData pd2 = PayData
                .builder()
                .billingDate(LocalDate.of(2024, 1, 5))
                .payAmount(10_000)
                .build();
        asserExpiryDate(pd2, LocalDate.of(2024, 2, 5));
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        PayData pd = PayData
                .builder()
                .billingDate(LocalDate.of(2024, 1, 31))
                .payAmount(10_000)
                .build();
        asserExpiryDate(pd, LocalDate.of(2024, 2, 29));
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부() {
        PayData pd1 = PayData
                .builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(10_000)
                .build();
        asserExpiryDate(pd1, LocalDate.of(2019, 3, 31));

        PayData pd2 = PayData
                .builder()
                .firstBillingDate(LocalDate.of(2019, 1, 30))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(10_000)
                .build();
        asserExpiryDate(pd2, LocalDate.of(2019, 3, 30));

        PayData pd3 = PayData
                .builder()
                .firstBillingDate(LocalDate.of(2023, 5, 31))
                .billingDate(LocalDate.of(2023, 6, 30))
                .payAmount(10_000)
                .build();
        asserExpiryDate(pd3, LocalDate.of(2023, 7, 31));
    }

    @Test
    void 이만원_이상_납부하면_비례해서_만료일_계산() {
        asserExpiryDate(
                PayData.builder()
                    .billingDate(LocalDate.of(2023, 3, 1))
                    .payAmount(20_000)
                    .build()
                , LocalDate.of(2023, 5, 1));
        asserExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2023, 3, 1))
                        .payAmount(30_000)
                        .build()
                , LocalDate.of(2023, 6, 1));
        asserExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2023, 3, 1))
                        .payAmount(50_000)
                        .build()
                , LocalDate.of(2023, 8, 1));
        asserExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2023, 3, 1))
                        .payAmount(70_000)
                        .build()
                , LocalDate.of(2023, 10, 1));
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_이만원_이상_납부() {
        asserExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2023, 1, 31))
                        .billingDate(LocalDate.of(2023, 2, 28))
                        .payAmount(20_000)
                        .build()
                , LocalDate.of(2023, 4, 30));
        asserExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2023, 1, 31))
                        .billingDate(LocalDate.of(2023, 2, 28))
                        .payAmount(40_000)
                        .build()
                , LocalDate.of(2023, 6, 30));
        asserExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2023, 3, 31))
                        .billingDate(LocalDate.of(2023, 4, 30))
                        .payAmount(30_000)
                        .build()
                , LocalDate.of(2023, 7, 31));
    }

    @Test
    void 십만원을_납부하면_1년_제공() {
        asserExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2023, 1, 28))
                        .payAmount(100_000)
                        .build()
                , LocalDate.of(2024, 1, 28));
    }

    private void asserExpiryDate(PayData paydata, LocalDate expectedExpiryDate) {
        ExpiryCalculator cal = new ExpiryCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(paydata);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }
}
