package net.william278.toilet;

import lombok.Builder;
import lombok.Getter;
import net.william278.toilet.dump.PluginInfo;
import net.william278.toilet.dump.ProjectMeta;
import net.william278.toilet.file.ConfigFileReader;
import net.william278.toilet.file.FileReader;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

// todo - when implementing a plugin, make this a config serializable object, probably.
@Getter
@Builder
public class DumpOptions {

    private final String byteBucketUrl;
    private final String viewerUrl;
    private final ProjectMeta projectMeta;
    private final List<CompatibilityRule> compatibilityRules;
    private final List<FileInclusionRule> fileInclusionRules;

    @Getter
    @Builder
    public static final class CompatibilityRule {

        private final String resourceName;
        private final PluginInfo.Label labelToApply;

    }

    @Getter
    @Builder
    public static final class FileInclusionRule {

        private final FileMeta fileMeta;

        @Builder.Default
        private final FileReader fileReader = new ConfigFileReader();

        public record FileMeta(@NotNull String filePath, @NotNull String fileLabel) {

            @NotNull
            public Path getPath() {
                return Paths.get(filePath);
            }

        }

    }

}
