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

package net.william278.toilet.web;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPOutputStream;

public interface Flusher {

    @NotNull
    default String sendPostRequest(@NotNull String content, @NotNull String bytebucketUrl) throws IOException {
        final HttpURLConnection connection = createConnection(bytebucketUrl);
        byte[] compressedContent = compressContent(content);
        sendRequestData(connection, compressedContent);

        int responseCode = connection.getResponseCode();
        checkResponseCode(responseCode);
        final String locationHeader = getLocationHeader(connection);
        connection.disconnect();
        return locationHeader;
    }

    @NotNull
    private HttpURLConnection createConnection(@NotNull String bytebucketUrl) throws IOException {
        final URL url = new URL("%s/post".formatted(bytebucketUrl));
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("User-Agent", "william278/toilet-java-client/huskhomes");
        connection.setRequestProperty("Content-Encoding", "gzip");
        connection.setDoOutput(true);
        return connection;
    }

    private byte[] compressContent(String content) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
            gzipOutputStream.write(content.getBytes(StandardCharsets.UTF_8));
            gzipOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        }
    }

    private void sendRequestData(HttpURLConnection connection, byte[] compressedContent) throws IOException {
        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(compressedContent);
        }
    }

    private void checkResponseCode(int responseCode) throws IOException {
        if (responseCode >= 400 && responseCode < 600) {
            throw new IOException("Server returned error code: %s".formatted(responseCode));
        }
    }

    @NotNull
    private String getLocationHeader(HttpURLConnection connection) throws IOException {
        String locationHeader = connection.getHeaderField("Location");
        if (locationHeader == null) {
            throw new IOException("Location header is missing from the response");
        }
        return locationHeader;
    }

}
