package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlUtils;
import ru.netology.page.DebitPage;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebitTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
    }
    @AfterEach
    public void clearDB() {
        SqlUtils.clearTables();
    }
    //Happy path

    @Test
    @DisplayName("Покупка по карте APPROVED")
    void shouldConfirmValidDebitCard() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getApprovedCardInfo());
        debitPage.waitForSuccessedNotification();
        assertEquals("APPROVED", SqlUtils.findPaymentStatus());
    }

    @Test
    @DisplayName("Покупка по карте DECLINED")
    void shouldDeclineInvalidCard() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getDeclinedCardInfo());
        debitPage.waitForFailedNotification();
        assertEquals("DECLINED", SqlUtils.findPaymentStatus());
        }
}
