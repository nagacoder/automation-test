package com.darvin.playground.ui;

import com.darvin.playground.base.BaseUiTest;
import com.darvin.playground.questions.LoginQuestions;
import com.darvin.playground.tasks.Login;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Tag("ui")
@DisplayName("Login")
class LoginTest extends BaseUiTest {

    @Test
    @Tag("smoke")
    @DisplayName("Successful login with valid credentials")
    void successfulLogin() {
        sarah.attemptsTo(
                Login.withCredentials("standard_user", "secret_sauce"),
                Ensure.that(LoginQuestions.pageTitle()).isEqualTo("Products")
        );
    }

    @ParameterizedTest(name = "user=''{0}'' -> {2}")
    @DisplayName("Failed login with invalid credentials")
    @CsvSource({
            "locked_out_user, secret_sauce, 'Sorry, this user has been locked out'",
            "standard_user,   wrong_pass,   Username and password do not match",
            "'',              secret_sauce, Username is required"
    })
    void failedLogin(String username, String password, String error) {
        sarah.attemptsTo(
                Login.withCredentials(username, password),
                Ensure.that(LoginQuestions.errorMessage()).contains(error)
        );
    }
}
