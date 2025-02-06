package net.william278.toilet;

import lombok.Builder;
import net.william278.toilet.dump.PluginInfo;
import net.william278.toilet.dump.ProjectMeta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

// todo - when implementing a plugin, make this a config serializable object, probably.
@Builder
public class ToiletOptions {

    private final String byteBucketUrl;
    private final String viewerUrl;
    private final ProjectMeta projectMeta;
    private final List<CompatibilityRule> compatibilityRules;
    private final List<FileInclusionRule> fileInclusionRules;

    @Builder
    public static final class CompatibilityRule {

        private final String resourceName;
        private final PluginInfo.Label labelToApply;

    }

    @Builder
    public static final class FileInclusionRule {

        private final String filePath;
        private final String fileLabel;
        private final Function<String, String> fileReader = (file) -> {
            try {
                return String.join("\n", Files.readAllLines(Paths.get(file)));
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        };

    }

}
