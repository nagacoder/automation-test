package com.darvin.playground.sites.site3.ui;

import net.serenitybdd.screenplay.targets.Target;

public class LoginPage {
    public static final Target USERNAME = Target.the("username field").locatedBy("//input[@name='user-name']");
    public static final Target PASSWORD = Target.the("password field").locatedBy("//input[@name='password']");
    public static final Target LOGIN_BUTTON = Target.the("login button").locatedBy("//input[@type='submit']");
}
