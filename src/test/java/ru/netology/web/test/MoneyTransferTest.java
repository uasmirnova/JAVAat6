package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;


import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    DashboardPage dashboardPage;
    public int startBalance1;
    public int startBalance2;
    public int finishBalance1;
    public int finishBalance2;

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        int sum = 1;
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var codeInfo = DataHelper.getCodeInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        dashboardPage = verificationPage.validVerify(codeInfo);
        startBalance1 = dashboardPage.getBalanceFirst();
        startBalance2 = dashboardPage.getBalanceSecond();
        var transactionPage = dashboardPage.clickTransaction(DataHelper.getSecondCard());
        var cardNumber = DataHelper.getFirstCard().getNumber();
        var dashboardPage = transactionPage.validTransaction(Integer.toString(sum), String.valueOf(cardNumber));
        finishBalance1 = dashboardPage.getBalanceFirst();
        finishBalance2 = dashboardPage.getBalanceSecond();
        assertEquals(startBalance1 - sum, finishBalance1);
        assertEquals(startBalance2 + sum, finishBalance2);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCards2() {
        int sum = 1;
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var codeInfo = DataHelper.getCodeInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        dashboardPage = verificationPage.validVerify(codeInfo);
        startBalance1 = dashboardPage.getBalanceFirst();
        startBalance2 = dashboardPage.getBalanceSecond();
        var transactionPage = dashboardPage.clickTransaction(DataHelper.getFirstCard());
        var cardNumber = DataHelper.getSecondCard().getNumber();
        var dashboardPage = transactionPage.validTransaction(Integer.toString(sum), String.valueOf(cardNumber));
        finishBalance1 = dashboardPage.getBalanceFirst();
        finishBalance2 = dashboardPage.getBalanceSecond();
        assertEquals(startBalance1 + sum, finishBalance1);
        assertEquals(startBalance2 - sum, finishBalance2);
    }

    @Test //BUG
    void shouldNotTransferMoneyBetweenCardsOverLimit() {
        int sum = startBalance2 + 1;
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var codeInfo = DataHelper.getCodeInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        dashboardPage = verificationPage.validVerify(codeInfo);
        startBalance1 = dashboardPage.getBalanceFirst();
        startBalance2 = dashboardPage.getBalanceSecond();
        var transactionPage = dashboardPage.clickTransaction(DataHelper.getFirstCard());
        var cardNumber = DataHelper.getSecondCard().getNumber();
        transactionPage.invalidTransaction(Integer.toString(sum), String.valueOf(cardNumber));
    }

    @Test //BUG
    void shouldNotTransferMoneyBetweenOneCard() {
        int sum = 1;
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var codeInfo = DataHelper.getCodeInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        dashboardPage = verificationPage.validVerify(codeInfo);
        startBalance1 = dashboardPage.getBalanceFirst();
        startBalance2 = dashboardPage.getBalanceSecond();
        var transactionPage = dashboardPage.clickTransaction(DataHelper.getFirstCard());
        var cardNumber = DataHelper.getFirstCard().getNumber();
        transactionPage.invalidTransaction(Integer.toString(sum), String.valueOf(cardNumber));
    }

    @Test //BUG
    void shouldNotTransfer0MoneyBetweenCards() {
        int sum = 0;
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var codeInfo = DataHelper.getCodeInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        dashboardPage = verificationPage.validVerify(codeInfo);
        startBalance1 = dashboardPage.getBalanceFirst();
        startBalance2 = dashboardPage.getBalanceSecond();
        var transactionPage = dashboardPage.clickTransaction(DataHelper.getFirstCard());
        var cardNumber = DataHelper.getFirstCard().getNumber();
        transactionPage.invalidTransaction(Integer.toString(sum), String.valueOf(cardNumber));
    }
}

