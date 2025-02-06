package net.william278.toilet;

import lombok.Builder;
import net.william278.toilet.dump.PluginInfo;

import java.util.List;

// todo - when implementing a plugin, make this a config serializable object, probably.
@Builder
public class ToiletOptions {

    private final String byteBucketUrl;
    private final String viewerUrl;
    private final List<CompatibilityRule> compatibilityRules;
    private final List<ConfigInclusionRule> configInclusionRules;

    @Builder
    public static final class CompatibilityRule {

        private final String resourceName;
        private final PluginInfo.Label labelToApply;

    }

    @Builder
    public static final class ConfigInclusionRule {

        private final String filePath;
        private final String fileLabel;

    }

}
