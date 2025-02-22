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

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.william278.toilet.dump.Dump;
import net.william278.toilet.dump.DumpUser;
import net.william278.toilet.dump.Dumper;
import net.william278.toilet.dump.ProjectMeta;
import net.william278.toilet.web.Flusher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Toilet implements Dumper, Flusher {

    private DumpOptions options;

    @NotNull
    public final URI dump(@Nullable DumpUser dumper) {
        try {
            final Dump dump = createDump(dumper);
            final String json = createGson().toJson(dump);
            final String code = uploadDump(json, options.getBytebinUrl());
            return URI.create("%s/%s".formatted(options.getBytebinUrl(), code));
        } catch (Throwable e) {
            throw new IllegalStateException("Failed to create dump: %s".formatted(e.getCause()), e);
        }
    }

    @NotNull
    public final URI dump() {
        return this.dump(null);
    }

    @NotNull
    private Gson createGson() {
        return Converters.registerOffsetDateTime(new GsonBuilder()).create();
    }

    @Override
    @NotNull
    public final ProjectMeta getProjectMeta() {
        return options.getProjectMeta();
    }

    @Override
    @NotNull
    public Path getProjectConfigDirectory() {
        return Path.of(System.getProperty("user.dir")).resolve("plugins").resolve(getProjectMeta().getName());
    }

    @Override
    @NotNull
    public String getLatestLog() {
        try {
            return Files.readString(Path.of(System.getProperty("user.dir")).resolve("latest.log"));
        } catch (IOException e) {
            return "Failed to read latest.log";
        }
    }

}
