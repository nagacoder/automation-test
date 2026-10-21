package com.darvin.playground.sites.site2.ui;

import net.serenitybdd.screenplay.targets.Target;

public class LoginPage {
    public static final Target USERNAME = Target.the("username field").locatedBy("[data-test='username']");
    public static final Target PASSWORD = Target.the("password field").locatedBy("[data-test='password']");
    public static final Target LOGIN_BUTTON = Target.the("login button").locatedBy("[data-test='login-button']");
}
