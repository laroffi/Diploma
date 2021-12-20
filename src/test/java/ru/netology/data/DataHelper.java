package ru.netology.data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataHelper {
    static Faker faker = new Faker(new Locale("en"));

    private DataHelper () {
    }

    public static CardInfo getApprovedCardInfo() {
        return new CardInfo("4444 4444 4444 4441", "23", "10", faker.name().fullName(), "105");
    }
    public static CardInfo getDeclinedCardInfo() {
        return new CardInfo("4444 4444 4444 4442", "22", "07", faker.name().fullName(), "978");
    }
    public static CardInfo getWrongCardInfo() {
        return new CardInfo("4444 4444 4444 4444", "22", "08", faker.name().fullName(), "999");
    }
    public static CardInfo getShortCardInfo() {
        return new CardInfo("4444 4444 4444 444", "23", "10", faker.name().fullName(), "105");
    }
    public static CardInfo getExpiredYearCardInfo() {
        return new CardInfo("4444 4444 4444 4441", "20", "11", faker.name().fullName(), "234");
    }
    public static CardInfo getFutureYearCardInfo() {
        return new CardInfo("4444 4444 4444 4441", "28", "09", faker.name().fullName(), "111");
    }
    public static CardInfo getExpiredMonthCardInfo() {
        return new CardInfo("4444 4444 4444 4441", "25", "10", faker.name().fullName(), "125");
    }
    public static CardInfo getWrongMonthCardInfo() {
        return new CardInfo("4444 4444 4444 4441", "23", "17", faker.name().fullName(), "123");
    }
    public static CardInfo getNullCardInfo() {
        return new CardInfo("", "23", "10", faker.name().fullName(), "105");
    }
    public static CardInfo getNotFilledCardInfo() {
        return new CardInfo("", "", "", "", "");
    }
    public static CardInfo getRusOwnerCardInfo() {
        return new CardInfo("4444 4444 4444 4441", "23", "10", "Лара Крофт", "105");
    }
    public static CardInfo getWrongOwnerCardInfo() {
        return new CardInfo("4444 4444 4444 4441", "23", "10", "111/?#", "105");
    }
    public static CardInfo getShortCVCCardInfo() {
        return new CardInfo("4444 4444 4444 4441", "23", "10", faker.name().fullName(), "01");
    }

}
