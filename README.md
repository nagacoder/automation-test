# Automation Test — Serenity BDD + JUnit 5 (Screenplay)

Java 21 · Maven · Serenity BDD 4.2.34 · JUnit 5

- **UI**: https://www.saucedemo.com (login)

## Structure

```
src/main/java/com/darvin/playground/      Framework code (reusable, no tests)
├── base/             BaseTest (extensions), BaseUiTest (browser), BaseMultiSiteTest (one browser per site)
├── listeners/        TestRunListener (whole run), TestLifecycleExtension (per test)
├── interactions/     Paced: adds interaction.delay after each interaction
├── sites/            Website contract + one module per site (site1/, site2/, site3/)
├── tasks/            Business actions (Login, NavigateTo)
├── questions/        What an actor can check (page title, error message)
├── ui/               Locators (Targets) per page
└── config/           Env, SiteConfig: read values / site connection details from serenity.conf

src/test/
├── java/com/darvin/playground/           Test classes (*Test.java)
│   └── ui/
└── resources/
    ├── serenity.conf     Browser, base URLs, environments
    └── META-INF/services/org.junit.platform.launcher.TestExecutionListener
```

## Hooks

| Where | When it runs | Use for |
|---|---|---|
| `TestRunListener.testPlanExecutionStarted` | once, before all tests | global setup, env checks, seed data |
| `TestRunListener.testPlanExecutionFinished` | once, after all tests | cleanup, summary, notifications |
| `TestLifecycleExtension.beforeAll` | before each test class | class-level setup |
| `TestLifecycleExtension.beforeEach` / `afterEach` | around each test | per-test setup/teardown |
| `TestLifecycleExtension.testFailed` | on a failed test | extra logging, attachments |
| `@BeforeEach` in `BaseUiTest` / `BaseMultiSiteTest` | before each test | give actors a browser |

## Run

```bash
mvn clean verify                          # all tests
mvn clean verify -Dgroups=smoke           # by @Tag (smoke, ui, comparison)
mvn clean verify -Dit.test=LoginTest      # one class
mvn clean verify -Dheadless.mode=false    # watch the browser
mvn clean verify -Denvironment=staging    # use another environment in serenity.conf
```

Report: `target/site/serenity/index.html`

## Adding a test

1. Create a `*Test.java` class under `src/test/java/.../ui`, extending `BaseUiTest` (or `BaseMultiSiteTest` for several sites).
2. Add locators in `ui/`, actions in `tasks/`, checks in `questions/` (all under `src/main/java`).

## Comparing feature A across websites

`FeatureAComparisonTest` logs in to each website (own actor, own browser), walks to feature A,
reads its text and checks that every target site shows the same text as the main site.
A side-by-side table is added to the Serenity report.

- **Config** (`serenity.conf → sites.<key>`): only name, url, username, password.
  Env vars `SITE2_URL` / `SITE2_USERNAME` / `SITE2_PASSWORD` (etc.) win over the file.
- **Module** (`sites/<key>/`): everything site-specific, implementing `sites/Website`:
  `ui/` locators, `tasks/` login and the steps to reach feature A, and the feature A question.
  The steps to feature A end by waiting for it, so a broken flow fails as "not reached", not as a mismatch.
- **Choose sites in code:** one test method per combination, e.g. `compare(new Site1(), new Site2())`.
- **Choose sites in config:** `website1VsConfiguredSites` uses `comparison.main` / `comparison.targets`
  from `serenity.conf`, or per run `-Dcomparison.targets=site2`.
- **Add a site:** add a `sites.siteN` block, create `sites/siteN/` with a `SiteN implements Website`,
  and register it in `sites/Websites`.
- **Speed:** `interaction.delay` (ms) pauses after each UI interaction; `-Dinteraction.delay=0` turns it off.

```bash
mvn clean verify -Dgroups=comparison                                       # every comparison test
mvn clean verify "-Dit.test=FeatureAComparisonTest#website1VsWebsite2"     # one combination
mvn clean verify "-Dit.test=FeatureAComparisonTest#website1VsConfiguredSites" -Dcomparison.targets=site3
```

> `maven-failsafe-plugin` is pinned to 3.2.5 on purpose. Keep it there unless you
> re-check, after an upgrade, that a failing test still fails the build.
