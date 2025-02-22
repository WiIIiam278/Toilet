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

package net.william278.toilet.fabric;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.Person;
import net.minecraft.server.MinecraftServer;
import net.william278.toilet.DumpOptions;
import net.william278.toilet.Toilet;
import net.william278.toilet.dump.PluginInfo;
import net.william278.toilet.dump.ServerMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class FabricToilet extends Toilet {

    private static final String SERVER_TYPE = "fabric";
    private static final String PROXY_MOD = "fabricproxy-lite";
    private final MinecraftServer server;

    private FabricToilet(@NotNull DumpOptions options, @NotNull MinecraftServer server) {
        super(options);
        this.server = server;
    }

    @NotNull
    public static FabricToilet create(@NotNull DumpOptions options, @NotNull MinecraftServer server) {
        return new FabricToilet(options, server);
    }

    @Override
    @NotNull
    @Unmodifiable
    public List<PluginInfo> getPlugins() {
        final FabricLoader instance = FabricLoader.getInstance();
        return instance.getAllMods().stream()
                .map(mod -> PluginInfo.builder()
                        .name(mod.getMetadata().getName())
                        .labels(getOptions().getLabelsFor(mod.getMetadata().getName(),
                                mod.getMetadata().getVersion().getFriendlyString()))
                        .version(mod.getMetadata().getVersion().toString())
                        .description(mod.getMetadata().getDescription())
                        .enabled(instance.isModLoaded(mod.getMetadata().getId()))
                        .authors(mod.getMetadata().getAuthors().stream().map(Person::getName).toList()).build())
                .toList();
    }

    @Override
    @NotNull
    public ServerMeta getServerMeta() {
        final FabricLoader instance = FabricLoader.getInstance();
        return ServerMeta.builder()
                .minecraftVersion(server.getVersion())
                .serverJarType(SERVER_TYPE)
                .serverJarVersion(instance.getModContainer("fabricloader").map(m -> m.getMetadata()
                        .getVersion().getFriendlyString()).orElse("unknown"))
                .proxyState(instance.isModLoaded(PROXY_MOD)
                        ? ServerMeta.ProxyState.BEHIND_VELOCITY_PROXY : ServerMeta.ProxyState.NO_PROXY)
                .onlineMode(server.isOnlineMode())
                .build();
    }

    @Override
    @NotNull
    public String getLatestLog() {
        try {
            final FabricLoader instance = FabricLoader.getInstance();
            return Files.readString(instance.getGameDir().resolve("latest.log"));
        } catch (IOException e) {
            return "Failed to read latest.log";
        }
    }

}
