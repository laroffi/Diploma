package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPage {
    private SelenideElement creditHeading = $$("h3").find(Condition.text("Кредит по данным карты"));
    private SelenideElement cardNumberField = $(byText("Номер карты")).parent().$(".input__control");
    private SelenideElement monthField = $(byText("Месяц")).parent().$(".input__control");
    private SelenideElement yearField = $(byText("Год")).parent().$(".input__control");
    private SelenideElement ownerField = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement CVCField = $(byText("CVC/CVV")).parent().$(".input__control");

    private SelenideElement continueButton = $(byText("Продолжить"));

    private SelenideElement successedNotification = $(byText("Операция одобрена Банком."));
    private SelenideElement failedNotification = $(byText("Ошибка! Банк отказал в проведении операции."));

    private SelenideElement wrongCardField = $(byText("Номер карты")).parent().$(".input__sub");
    private SelenideElement wrongMonthField = $(byText("Месяц")).parent().$(".input__sub");
    private SelenideElement wrongYearField = $(byText("Год")).parent().$(".input__sub");
    private SelenideElement wrongOwnerField = $(byText("Владелец")).parent().$(".input__sub");
    private SelenideElement wrongCVCField = $(byText("CVC/CVV")).parent().$(".input__sub");
    private SelenideElement expiredCardNotification = $(byText("Истёк срок действия карты"));

    public CreditPage() {
        creditHeading.shouldBe(Condition.visible);
    }
    public void fillForm (CardInfo cardInfo) {
        cardNumberField.setValue(cardInfo.getCardNumber());
        monthField.setValue(cardInfo.getMonth());
        yearField.setValue(cardInfo.getYear());
        ownerField.setValue(cardInfo.getOwner());
        CVCField.setValue(cardInfo.getCVC());
        continueButton.click();
    }
    public void notFilledForm () {
        continueButton.click();
        wrongCardField.shouldBe(Condition.visible);
        wrongMonthField.shouldBe(Condition.visible);
        wrongYearField.shouldBe(Condition.visible);
        wrongOwnerField.shouldBe(Condition.visible);
        wrongCVCField.shouldBe(Condition.visible);
    }

    public void waitForSuccessedNotification() {
        successedNotification.shouldBe(Condition.visible, Duration.ofSeconds(20));
    }
    public void waitForFailedNotification() {
        failedNotification.shouldBe(Condition.visible, Duration.ofSeconds(20));
    }
    public void cardNumberFail () {
        wrongCardField.shouldBe(Condition.visible);
    }
    public void monthFail () {
        wrongMonthField.shouldBe(Condition.visible);
    }
    public void yearFail () {
        wrongYearField.shouldBe(Condition.visible);
    }
    public void ownerFail () {
        wrongOwnerField.shouldBe(Condition.visible, Duration.ofSeconds(20));
    }
    public void CVCFail () {
        wrongCVCField.shouldBe(Condition.visible);
    }
    public void expiredFail() { expiredCardNotification.shouldBe(Condition.visible); }
}