package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection transactionButtons = $$("button[data-test-id=action-deposit]");
    private ElementsCollection cards = $$(".list__item div");
    private SelenideElement card1 = $("div[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private SelenideElement card2 = $("div[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");


    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public TransactionPage clickTransaction(DataHelper.CardInfo info) {
        cards.findBy(attribute("data-test-id", info.getTestID())).$("button").click();
        return new TransactionPage();
    }

    public int getBalanceFirst() {
        String [] text = card1.innerText().split(" ");
        return Integer.parseInt(text[5]);
    }

    public int getBalanceSecond() {
        String [] text = card2.innerText().split(" ");
        return Integer.parseInt(text[5]);
    }
}
