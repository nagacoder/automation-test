package com.darvin.playground.listeners;

import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.TestPlan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Global listener for the whole test run (registered in
 * META-INF/services/org.junit.platform.launcher.TestExecutionListener).
 * Use it for one-off setup/teardown: test data, environment checks, notifications.
 */
public class TestRunListener implements TestExecutionListener {

    private static final Logger LOG = LoggerFactory.getLogger(TestRunListener.class);

    private final AtomicInteger passed = new AtomicInteger();
    private final AtomicInteger failed = new AtomicInteger();
    private final AtomicInteger skipped = new AtomicInteger();
    private Instant startedAt;

    @Override
    public void testPlanExecutionStarted(TestPlan testPlan) {
        startedAt = Instant.now();
        LOG.info("===== Test run started (environment: {}) =====",
                System.getProperty("environment", "default"));
    }

    @Override
    public void executionSkipped(TestIdentifier test, String reason) {
        if (test.isTest()) {
            skipped.incrementAndGet();
        }
    }

    @Override
    public void executionFinished(TestIdentifier test, TestExecutionResult result) {
        if (!test.isTest()) {
            return;
        }
        if (result.getStatus() == TestExecutionResult.Status.SUCCESSFUL) {
            passed.incrementAndGet();
        } else {
            failed.incrementAndGet();
        }
    }

    @Override
    public void testPlanExecutionFinished(TestPlan testPlan) {
        LOG.info("===== Test run finished in {}s: {} passed, {} failed, {} skipped =====",
                Duration.between(startedAt, Instant.now()).toSeconds(),
                passed.get(), failed.get(), skipped.get());
    }
}
