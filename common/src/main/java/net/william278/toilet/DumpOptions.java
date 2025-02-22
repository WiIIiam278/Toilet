/*
 * This file is part of HuskSync, licensed under the Apache License 2.0.
 *
 *  Copyright (c) William278 <will27528@gmail.com>
 *  Copyright (c) QarthO <QarthO@gmail.com>
 *  Copyright (c) contributors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

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

    private final String bytebinUrl;
    private final String viewerUrl;
    private final ProjectMeta projectMeta;
    private final List<CompatibilityRule> compatibilityRules;
    private final List<FileInclusionRule> fileInclusionRules;

    @NotNull
    public List<PluginInfo.Label> getLabelsFor(@NotNull String pluginName,
                                               @NotNull @SuppressWarnings("unused") String pluginVersion) {
        return compatibilityRules.stream()
                .filter(rule -> rule.getResourceName().equalsIgnoreCase(pluginName))
                .map(CompatibilityRule::getLabelToApply)
                .toList();
    }

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

        @NotNull
        public static FileInclusionRule configFile(@NotNull String name, @NotNull String label) {
            return FileInclusionRule.builder().fileMeta(new FileMeta(name, label)).build();
        }

        public record FileMeta(@NotNull String filePath, @NotNull String fileLabel) {

            @NotNull
            public Path getPath() {
                return Paths.get(filePath);
            }

        }

    }

}
