package com.darvin.playground.questions;

import com.darvin.playground.ui.InventoryPage;
import com.darvin.playground.ui.LoginPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class LoginQuestions {
    public static Question<String> pageTitle() {
        return Text.of(InventoryPage.TITLE).describedAs("the page title");
    }

    public static Question<String> errorMessage() {
        return Text.of(LoginPage.ERROR_MESSAGE).describedAs("the error message");
    }
}
