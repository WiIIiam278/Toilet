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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PluginInfo {

    public static final Label INCOMPATIBLE_LABEL = new Label("Incompatible", "#ff3300");
    public static final Label WARNING_LABEL = new Label("Warning", "#ffcc00");

    private String name;
    private String version;
    private String description;
    private List<String> authors;
    private boolean enabled;
    private List<Label> labels;

    @NoArgsConstructor
    @AllArgsConstructor
    public static final class Label {
        private String name;
        private String color;
    }

}
