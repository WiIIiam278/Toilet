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

package net.william278.toilet.velocity;

import com.velocitypowered.api.proxy.ProxyServer;
import net.william278.toilet.DumpOptions;
import net.william278.toilet.Toilet;
import net.william278.toilet.dump.PluginInfo;
import net.william278.toilet.dump.ServerMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

@SuppressWarnings("unused")
public class VelocityToilet extends Toilet {

    private final ProxyServer server;

    private VelocityToilet(@NotNull DumpOptions options, @NotNull ProxyServer server) {
        super(options);
        this.server = server;
    }

    @NotNull
    public static VelocityToilet create(@NotNull DumpOptions options, @NotNull ProxyServer server) {
        return new VelocityToilet(options, server);
    }

    @Override
    @NotNull
    @Unmodifiable
    public List<PluginInfo> getPlugins() {
        return server.getPluginManager().getPlugins().stream()
                .map(plugin -> PluginInfo.builder()
                        .name(plugin.getDescription().getName().orElse(plugin.getDescription().getId()))
                        .labels(getOptions().getLabelsFor(plugin.getDescription().getId(),
                                plugin.getDescription().getVersion().orElse("unknown")))
                        .version(plugin.getDescription().getVersion().orElse("unknown"))
                        .description(plugin.getDescription().getDescription().orElse(""))
                        .enabled(server.getPluginManager().isLoaded(plugin.getDescription().getId()))
                        .authors(plugin.getDescription().getAuthors()).build())
                .toList();
    }

    @Override
    @NotNull
    public ServerMeta getServerMeta() {
        return ServerMeta.builder()
                .serverJarType(server.getVersion().getName())
                .serverJarVersion(server.getVersion().getVersion())
                .onlineMode(server.getConfiguration().isOnlineMode())
                .build();
    }

}
