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

import net.william278.toilet.dump.PluginInfo;
import net.william278.toilet.dump.ServerMeta;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public class BukkitToilet extends Toilet {

    private BukkitToilet(@NotNull DumpOptions options) {
        super(options);
    }

    @NotNull
    public static BukkitToilet create(@NotNull DumpOptions options) {
        return new BukkitToilet(options);
    }

    @Override
    @NotNull
    @Unmodifiable
    public List<PluginInfo> getPlugins() {
        return Arrays.stream(Bukkit.getServer().getPluginManager().getPlugins())
                .map(plugin -> PluginInfo.builder()
                        .name(plugin.getName())
                        .labels(getOptions().getLabelsFor(plugin.getName(), plugin.getDescription().getVersion()))
                        .version(plugin.getDescription().getVersion())
                        .description(plugin.getDescription().getDescription())
                        .enabled(plugin.isEnabled())
                        .authors(plugin.getDescription().getAuthors()).build())
                .toList();
    }

    @Override
    @NotNull
    public ServerMeta getServerMeta() {
        return ServerMeta.builder()
                .minecraftVersion(Bukkit.getServer().getVersion())
                .serverJarType(Bukkit.getServer().getName())
                .serverJarVersion(Bukkit.getServer().getBukkitVersion())
                .proxyState(ServerMeta.ProxyState.UNKNOWN)
                .onlineMode(Bukkit.getServer().getOnlineMode())
                .build();
    }

    @Override
    @NotNull
    public Path getProjectConfigDirectory() {
        return Bukkit.getWorldContainer().toPath().resolve("plugins").resolve(getProjectMeta().getName());
    }

    @Override
    @NotNull
    public String getLatestLog() {
        try {
            return Files.readString(Bukkit.getWorldContainer().toPath()
                    .resolve("logs").resolve("latest.log"));
        } catch (IOException e) {
            return "Failed to read latest.log";
        }
    }

}
