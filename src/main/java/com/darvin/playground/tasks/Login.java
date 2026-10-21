package com.darvin.playground.tasks;

import com.darvin.playground.interactions.Paced;
import com.darvin.playground.ui.LoginPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class Login {
    public static Performable withCredentials(String username, String password) {
        return Task.where("{0} logs in as " + username,
                Paced.steps(
                        Enter.theValue(username).into(LoginPage.USERNAME),
                        Enter.theValue(password).into(LoginPage.PASSWORD),
                        Click.on(LoginPage.LOGIN_BUTTON)
                ));
    }
}
