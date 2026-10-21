package com.darvin.playground.base;

import com.darvin.playground.listeners.TestLifecycleExtension;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.extension.ExtendWith;

/** Common setup for all tests: Serenity reporting + lifecycle hooks. */
@ExtendWith({SerenityJUnit5Extension.class, TestLifecycleExtension.class})
public abstract class BaseTest {
}
