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

import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("unused")
public class PluginStatus {

    @Builder.Default
    private Map<String, String> status = new HashMap<>();

    @Builder.Default
    private List<StatusBlock> blocks = new ArrayList<>();

    public PluginStatus(@NotNull Map<String, String> status) {
        this.status = status;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static abstract class StatusBlock {
        private BlockType type;
        private String label;
        private String icon;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ListStatusBlock extends StatusBlock {
        private List<String> status = new ArrayList<>();

        public ListStatusBlock(@NotNull List<String> status,
                               @NotNull String label, @NotNull String icon) {
            super(BlockType.LIST, label, icon);
            this.status = status;
        }
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class MapStatusBlock extends StatusBlock {
        private Map<String, String> status = new HashMap<>();

        public MapStatusBlock(@NotNull Map<String, String> status,
                              @NotNull String label, @NotNull String icon) {
            super(BlockType.MAP, label, icon);
            this.status = status;
        }
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class TableStatusBlock extends StatusBlock {
        private List<List<String>> status = new ArrayList<>();

        public TableStatusBlock(@NotNull List<List<String>> status,
                                @NotNull String label, @NotNull String icon) {
            super(BlockType.TABLE, label, icon);
            this.status = status;
        }
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ChartStatusBlock extends StatusBlock {
        private Map<ChartKey, Integer> values = new HashMap<>();
        private ChartType type;

        public ChartStatusBlock(@NotNull Map<ChartKey, Integer> values, @NotNull ChartType type,
                                @NotNull String label, @NotNull String icon) {
            super(BlockType.CHART, label, icon);
            this.values = values;
            this.type = type;
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ChartKey {
        private String label;
        private @Nullable String icon;
        private int color = 0xffffff;

        public ChartKey(@NotNull String label) {
            this.label = label;
        }
    }

    public enum ChartType {
        BAR,
        PIE
    }

    public enum BlockType {
        LIST,
        MAP,
        CHART,
        TABLE
    }

}
