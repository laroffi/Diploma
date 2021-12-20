package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.mysql.jdbc.Driver;
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

    @Test
    @DisplayName("Payment with card APPROVED")
    void shouldConfirmValidDebitCard() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getApprovedCardInfo());
        debitPage.waitForSuccessedNotification();
        assertEquals("APPROVED", SqlUtils.findPaymentStatus());
    }
    @Test
    @DisplayName("Payment with card DECLINED")
    void shouldDeclineInvalidCard() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getDeclinedCardInfo());
        debitPage.waitForFailedNotification();
        assertEquals("DECLINED", SqlUtils.findPaymentStatus());
        }
    @Test
    @DisplayName("Payment with wrong Card")
    void shouldFailWrongCard() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getWrongCardInfo());
        debitPage.waitForFailedNotification();
        assertEquals("0", SqlUtils.findCountOrderEntity());
        }
    @Test
    @DisplayName("Payment with short number Card")
    void shouldFailShortNumberCard() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getShortCardInfo());
        debitPage.cardNumberFail();
        assertEquals("0", SqlUtils.findCountOrderEntity());
    }
    @Test
    @DisplayName("Payment with expired year Card")
    void shouldFailExpiredYearCard() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getExpiredYearCardInfo());
        debitPage.expiredFail();
        assertEquals("0", SqlUtils.findCountOrderEntity());
    }
    @Test
    @DisplayName("Payment with expired month Card")
    void shouldFailExpiredMonthCard() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getExpiredMonthCardInfo());
        debitPage.expiredFail();
        assertEquals("0", SqlUtils.findCountOrderEntity());
    }
    @Test
    @DisplayName("Payment with future Card year")
    void shouldFailFutureYearCard() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getFutureYearCardInfo());
        debitPage.yearFail();
        assertEquals("0", SqlUtils.findCountOrderEntity());
    }
    @Test
    @DisplayName("Payment with not filled Card number")
    void shouldFailEmptyCard() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getNullCardInfo());
        debitPage.cardNumberFail();
        assertEquals("0", SqlUtils.findCountOrderEntity());
    }
    @Test
    @DisplayName("Payment with cyrillic name Card")
    void shouldFailCyrillicNameCard() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getRusOwnerCardInfo());
        debitPage.ownerFail();
        assertEquals("0", SqlUtils.findCountOrderEntity());
    }
    @Test
    @DisplayName("Payment with wrong month Card")
    void shouldFailWrongMonthCard() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getWrongMonthCardInfo());
        debitPage.monthFail();
        assertEquals("0", SqlUtils.findCountOrderEntity());
    }

    @Test
    @DisplayName("Payment with symbols name Card")
    void shouldFailSymbolsNameCard() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getWrongOwnerCardInfo());
        debitPage.ownerFail();
        assertEquals("0", SqlUtils.findCountOrderEntity());
    }
    @Test
    @DisplayName("Payment with short CVC Card")
    void shouldFailShortCVCCard() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getShortCVCCardInfo());
        debitPage.CVCFail();
        assertEquals("0", SqlUtils.findCountOrderEntity());
    }
    @Test
    @DisplayName("Payment with empty fields Card")
    void shouldFailFullyEmptyCard() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getNullCardInfo());
        debitPage.notFilledForm();
        assertEquals("0", SqlUtils.findCountOrderEntity());
    }
}
