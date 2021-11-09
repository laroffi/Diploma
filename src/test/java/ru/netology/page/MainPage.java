package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private SelenideElement debitButton = $(byText("Купить"));
    private SelenideElement creditButton = $(byText("Купить в кредит"));
    private SelenideElement heading = $(byText("Путешествие дня"));

    public void StartMainPage() {
        heading.shouldBe(Condition.visible);
    }

    public DebitPage chooseDebitPage() {
        debitButton.click();
        return new DebitPage();
    }

    public CreditPage chooseCreditPage() {
        creditButton.click();
        return new CreditPage();
    }

}
