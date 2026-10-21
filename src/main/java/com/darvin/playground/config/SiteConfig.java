package com.darvin.playground.config;

/**
 * Connection details for one website: the sites.&lt;key&gt; block in serenity.conf.
 * Env vars SITE2_URL / SITE2_USERNAME / SITE2_PASSWORD (etc.) win over the file.
 */
public record SiteConfig(String key, String name, String url, String username, String password) {

    public static SiteConfig of(String key) {
        return new SiteConfig(
                key,
                Env.require("sites." + key + ".name"),
                value(key, "url"),
                value(key, "username"),
                value(key, "password"));
    }

    /** Read here rather than with HOCON ${?VAR}: Serenity leaves that unresolved when any key is passed with -D. */
    private static String value(String key, String field) {
        String fromEnv = System.getenv(key.toUpperCase() + "_" + field.toUpperCase());
        return fromEnv != null && !fromEnv.isBlank() ? fromEnv : Env.require("sites." + key + "." + field);
    }

    @Override
    public String toString() {
        // Keeps the password out of logs and reports
        return name + " (" + url + ")";
    }
}
