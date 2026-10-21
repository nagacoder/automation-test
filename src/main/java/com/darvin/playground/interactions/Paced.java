package com.darvin.playground.interactions;

import com.darvin.playground.config.Env;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.SilentInteraction;

import java.util.ArrayList;
import java.util.List;

/**
 * Adds the configured pause (interaction.delay in serenity.conf) after each interaction.
 * Use it inside tasks: {@code Task.where("...", Paced.steps(Enter..., Click...))}.
 */
public final class Paced {
    private Paced() {
    }

    public static Performable[] steps(Performable... interactions) {
        List<Performable> paced = new ArrayList<>();
        for (Performable interaction : interactions) {
            paced.add(interaction);
            paced.add(new Delay());
        }
        return paced.toArray(Performable[]::new);
    }

    /** Silent so the pauses don't clutter the Serenity report. */
    static class Delay extends SilentInteraction {
        @Override
        public <T extends Actor> void performAs(T actor) {
            long millis = Env.interactionDelayMillis();
            if (millis <= 0) {
                return;
            }
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
