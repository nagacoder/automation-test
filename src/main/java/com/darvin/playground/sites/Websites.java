package com.darvin.playground.sites;

import com.darvin.playground.config.Env;
import com.darvin.playground.sites.site1.Site1;
import com.darvin.playground.sites.site2.Site2;
import com.darvin.playground.sites.site3.Site3;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/** Picks website modules by key (site1, site2, ...). Register a new module here. */
public final class Websites {
    private Websites() {
    }

    // Suppliers so only the selected sites are built (and need their config)
    private static final Map<String, Supplier<Website>> MODULES = new LinkedHashMap<>();

    static {
        MODULES.put("site1", Site1::new);
        MODULES.put("site2", Site2::new);
        MODULES.put("site3", Site3::new);
    }

    public static Website byKey(String key) {
        Supplier<Website> module = MODULES.get(key.trim());
        if (module == null) {
            throw new IllegalArgumentException("Unknown website '" + key + "', known: " + MODULES.keySet());
        }
        return module.get();
    }

    /** The reference site: comparison.main, e.g. -Dcomparison.main=site1 */
    public static Website main() {
        return byKey(Env.require("comparison.main"));
    }

    /** The sites to compare: comparison.targets, comma separated, e.g. -Dcomparison.targets=site2 */
    public static List<Website> targets() {
        List<Website> targets = Arrays.stream(Env.require("comparison.targets").split(","))
                .map(String::trim)
                .filter(key -> !key.isEmpty())
                .map(Websites::byKey)
                .toList();
        if (targets.isEmpty()) {
            throw new IllegalStateException("comparison.targets is empty, e.g. -Dcomparison.targets=site2");
        }
        return targets;
    }
}
