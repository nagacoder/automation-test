package com.darvin.playground.tasks;

import com.darvin.playground.interactions.Paced;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class NavigateTo {
    public static Performable theLoginPage() {
        return Task.where("{0} opens the login page", Paced.steps(Open.relativeUrl("/")));
    }
}
