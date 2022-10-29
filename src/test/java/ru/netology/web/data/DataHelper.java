package ru.netology.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

public class DataHelper {

    @Data
    @AllArgsConstructor
    public static class AuthInfo {
        private final String login;
        private final String password;
        private final String verificationCode;


        public AuthInfo() {
            this.login = "vasya";
            this.password = "qwerty123";
            this.verificationCode = "12345";
        }
    }

    @Value
    public static class CardInfo {
        private String number;
    }

    public static CardInfo getFirstCard() {
        return new CardInfo("5559 0000 0000 0001");
    }

    public static CardInfo getSecondCard() {
        return new CardInfo("5559 0000 0000 0002");
    }
}
