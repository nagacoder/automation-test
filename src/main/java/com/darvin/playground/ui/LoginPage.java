package com.darvin.playground.ui;

import net.serenitybdd.screenplay.targets.Target;

public class LoginPage {
    public static final Target USERNAME = Target.the("username field").locatedBy("#user-name");
    public static final Target PASSWORD = Target.the("password field").locatedBy("#password");
    public static final Target LOGIN_BUTTON = Target.the("login button").locatedBy("#login-button");
    public static final Target ERROR_MESSAGE = Target.the("error message").locatedBy("[data-test='error']");
}
