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

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public final class FileFilterUtil {

    private static final Map<String, String> LOGS_FILTERS = Map.of(
            "(?:[0-9]{1,3}\\.){3}[0-9]{1,3}", "<Censored IP>"
    );

    private static final Map<String, String> PASSWORD_FILTERS = Map.of(
            "((password|PASSWORD|pass|PASS): ?('?\"?\\w+'?\"?))", "<Censored Password>",
            "(?:[0-9]{1,3}\\.){3}[0-9]{1,3}", "<Censored IP>"
    );

    @NotNull
    public static String filterLogs(@NotNull String logs) {
        for (Map.Entry<String, String> entry : LOGS_FILTERS.entrySet()) {
            logs = logs.replaceAll(entry.getKey(), entry.getValue());
        }
        return logs;
    }

    @NotNull
    public static String filterConfig(@NotNull String config) {
        for (Map.Entry<String, String> entry : PASSWORD_FILTERS.entrySet()) {
            config = config.replaceAll(entry.getKey(), entry.getValue());
        }
        return config;
    }
}
