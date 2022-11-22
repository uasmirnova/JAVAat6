package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public VerificationPage() {
        codeField.shouldBe(visible);
        verifyButton.shouldBe(visible);
    }

    public DashboardPage validVerify(DataHelper.CodeInfo info) {
        codeField.setValue(info.getVerificationCode());
        verifyButton.click();
        return new DashboardPage();
    }
}
