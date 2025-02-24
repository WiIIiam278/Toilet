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

import com.google.gson.*;
import net.william278.toilet.dump.PluginStatus;

import java.lang.reflect.Type;

public class StatusBlockDeserializer implements JsonDeserializer<PluginStatus.StatusBlock> {

    private static final Gson GSON = new Gson();

    @Override
    public PluginStatus.StatusBlock deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject object = json.getAsJsonObject();
        final PluginStatus.BlockType blockType = PluginStatus.BlockType.valueOf(object.get("type").getAsString());
        return switch (blockType) {
            case MAP -> GSON.fromJson(object, PluginStatus.MapStatusBlock.class);
            case LIST -> GSON.fromJson(object, PluginStatus.ListStatusBlock.class);
            case CHART -> GSON.fromJson(object, PluginStatus.ChartStatusBlock.class);
            case TABLE -> GSON.fromJson(object, PluginStatus.TableStatusBlock.class);
        };
    }

}
