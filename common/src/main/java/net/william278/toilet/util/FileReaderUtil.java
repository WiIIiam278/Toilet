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

package net.william278.toilet.util;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.StringJoiner;

public final class FileReaderUtil {

    private static final String LINE_DELIMITER = "\n";

    @NotNull
    public static String readLargeFile(@NotNull Path file, int linesToRead) throws IOException {
        final ReversedLinesFileReader.Builder builder = ReversedLinesFileReader.builder()
                .setPath(file).setCharset(StandardCharsets.UTF_8);
        final StringJoiner joiner = new StringJoiner(LINE_DELIMITER);
        try (ReversedLinesFileReader reader = builder.get()) {
            reader.readLines(linesToRead).forEach(joiner::add);
        }
        return joiner.toString();
    }

}
