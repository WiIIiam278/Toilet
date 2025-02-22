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

package net.william278.toilet.file;

import net.william278.toilet.DumpOptions;
import net.william278.toilet.dump.AttachedFile;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.Optional;

public final class DatabaseFileReader implements FileReader {

    @Override
    public Optional<AttachedFile> read(@NotNull DumpOptions.FileInclusionRule.FileMeta meta,
                                       @NotNull Path configDirectory) {
        return Optional.empty();
//        try {
//            final Path file = Paths.get(path);
//            if (Files.isRegularFile(file)) {
//                return Optional.of(Files.readString(file, StandardCharsets.UTF_8));
//            }
//            return Optional.empty();
//        } catch (IOException e) {
//            return Optional.empty();
//        }
    }

}
