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
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.william278.toilet.dump.*;

import java.io.IOException;
import java.lang.reflect.Type;

public class AttachedFileSerializer implements JsonSerializer<AttachedFile>, JsonDeserializer<AttachedFile> {

    private static final Gson GSON = new Gson();

    @Override
    public AttachedFile deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject object = json.getAsJsonObject();
        final AttachedFile.Type fileType = AttachedFile.Type.valueOf(object.get("type").getAsString());
        return switch (fileType) {
            case CONFIG_FILE -> GSON.fromJson(object, ConfigFile.class);
            case DATABASE_FILE -> GSON.fromJson(object, DatabaseFile.class);
            case EXTRA_FILE -> GSON.fromJson(object, ExtraFile.class);
        };
    }

    @Override
    public JsonElement serialize(AttachedFile file, Type type, JsonSerializationContext context) {
        return switch (file.getType()) {
            case CONFIG_FILE -> GSON.toJsonTree(file, ConfigFile.class);
            case DATABASE_FILE -> GSON.toJsonTree(file, DatabaseFile.class);
            case EXTRA_FILE -> GSON.toJsonTree(file, ExtraFile.class);
        };
    }
}
