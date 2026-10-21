package com.darvin.playground.listeners;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Per-class / per-test hooks. Applied to every test through {@link com.darvin.playground.base.BaseTest}.
 */
public class TestLifecycleExtension implements BeforeAllCallback, BeforeEachCallback, AfterEachCallback, TestWatcher {

    private static final Logger LOG = LoggerFactory.getLogger(TestLifecycleExtension.class);

    @Override
    public void beforeAll(ExtensionContext context) {
        LOG.info(">> Starting test class: {}", context.getDisplayName());
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        LOG.info(">> Starting test: {}", context.getDisplayName());
    }

    @Override
    public void afterEach(ExtensionContext context) {
        LOG.info("<< Finished test: {}", context.getDisplayName());
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        LOG.info("PASSED: {}", context.getDisplayName());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        LOG.error("FAILED: {} - {}", context.getDisplayName(), cause.getMessage());
    }
}
