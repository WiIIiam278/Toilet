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

package net.william278.toilet.dump;

import net.william278.toilet.DumpOptions;
import net.william278.toilet.file.ConfigDirectoryProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Dumper extends DumpMetaProvider, AttachedFileProvider, EnvironmentInfoProvider, PluginProvider, ProjectMetaProvider,
        ServerMetaProvider, LatestLogProvider, ConfigDirectoryProvider {

    @NotNull
    default Dump createDump(@Nullable DumpUser dumpCreator) {
        return Dump.builder()
                .meta(dumpCreator == null ? getDumpMeta() : getDumpMeta(dumpCreator))
                .server(getServerMeta())
                .project(getProjectMeta())
                .environment(getEnvironmentInfo())
                .plugins(getPlugins())
                .files(getAttachedFiles(getProjectConfigDirectory()))
                .latestLog(getLatestLog())
                .build();
    }

    @NotNull
    default Dump createDump() {
        return this.createDump(null);
    }

    @NotNull
    DumpOptions getOptions();

}
