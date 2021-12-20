package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlUtils;
import ru.netology.page.CreditPage;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditTest {
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
    @DisplayName("Credit with card APPROVED")
    void shouldConfirmValidCreditCard() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getApprovedCardInfo());
        creditPage.waitForSuccessedNotification();
        assertEquals("APPROVED", SqlUtils.findCreditRequestStatus());
    }
    @Test
    @DisplayName("Credit with card DECLINED")
    void shouldDeclineInvalidCard() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getDeclinedCardInfo());
        creditPage.waitForFailedNotification();
        assertEquals("DECLINED", SqlUtils.findCreditRequestStatus());
    }
    @Test
    @DisplayName("Credit with short number Card")
    void shouldFailShortNumberCard() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getShortCardInfo());
        creditPage.cardNumberFail();
        assertEquals("0", SqlUtils.findCountOrderEntity());
    }
    @Test
    @DisplayName("Credit with expired year Card")
    void shouldFailExpiredCard() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getExpiredYearCardInfo());
        creditPage.expiredFail();
        assertEquals("0", SqlUtils.findCountOrderEntity());
    }
    @Test
    @DisplayName("Credit with expired month Card")
    void shouldFailExpiredMonthCard() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getExpiredMonthCardInfo());
        creditPage.expiredFail();
        assertEquals("0", SqlUtils.findCountOrderEntity());
    }
    @Test
    @DisplayName("Credit with future Card number")
    void shouldFailFutureYearCard() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getFutureYearCardInfo());
        creditPage.yearFail();
        assertEquals("0", SqlUtils.findCountOrderEntity());
    }
    @Test
    @DisplayName("Credit with not filled Card number")
    void shouldFailEmptyCard() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getNullCardInfo());
        creditPage.cardNumberFail();
        assertEquals("0", SqlUtils.findCountOrderEntity());
    }
    @Test
    @DisplayName("Credit with cyrillic name Card")
    void shouldFailCyrillicNameCard() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getRusOwnerCardInfo());
        creditPage.ownerFail();
        assertEquals("0", SqlUtils.findCountOrderEntity());
    }
    @Test
    @DisplayName("Credit with wrong month Card")
    void shouldFailWrongMonthCard() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getWrongMonthCardInfo());
        creditPage.monthFail();
        assertEquals("0", SqlUtils.findCountOrderEntity());
    }
    @Test
    @DisplayName("Credit with symbols name Card")
    void shouldFailSymbolsNameCard() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getWrongOwnerCardInfo());
        creditPage.ownerFail();
        assertEquals("0", SqlUtils.findCountOrderEntity());
    }
    @Test
    @DisplayName("Credit with short CVC Card")
    void shouldFailShortCVCCard() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getShortCVCCardInfo());
        creditPage.CVCFail();
        assertEquals("0", SqlUtils.findCountOrderEntity());
    }
    @Test
    @DisplayName("Credit with empty fields Card")
    void shouldFailFullyEmptyCard() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getNotFilledCardInfo());
        creditPage.notFilledForm();
        assertEquals("0", SqlUtils.findCountOrderEntity());
    }
}
