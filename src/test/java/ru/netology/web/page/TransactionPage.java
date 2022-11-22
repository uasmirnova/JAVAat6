package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransactionPage {

    private SelenideElement sumField = $("div[data-test-id=amount] input");
    private SelenideElement accountField = $("span[data-test-id=from] input");
    private SelenideElement transactionButton = $("button[data-test-id=action-transfer]");
    private SelenideElement errorNotification = $("[data-test-id = error-notification]");

    public DashboardPage validTransaction(String sum, String cardNumber) {
        sumField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        sumField.setValue(sum);
        accountField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        accountField.setValue(cardNumber);
        transactionButton.click();
        return new DashboardPage();
    }

    public void invalidTransaction(String sum, String cardNumber) {
        sumField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        sumField.setValue(sum);
        errorNotification.shouldBe(visible);
    }
}
