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

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("File Util Tests")
public class FileUtilTests {

    private static final String LOG_FILE = "test_log.log";
    private static Path TEMP_DIR;
    private static Path TEMP_FILE;

    @BeforeAll
    static void setUp() throws IOException {
        InputStream resourceInputStream = FileUtilTests.class.getClassLoader().getResourceAsStream(LOG_FILE);
        if (resourceInputStream == null) throw new FileNotFoundException("Resource not found");

        TEMP_DIR = Files.createTempDirectory(Path.of(System.getProperty("java.io.tmpdir")), "tempResourceDir");
        TEMP_FILE = TEMP_DIR.resolve(LOG_FILE);

        try (OutputStream outputStream = new FileOutputStream(TEMP_FILE.toFile())) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = resourceInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        }
        resourceInputStream.close();
    }

    @AfterAll
    static void tearDown() throws IOException {
        if (Files.exists(TEMP_FILE)) Files.delete(TEMP_FILE);
        if (Files.exists(TEMP_DIR)) Files.delete(TEMP_DIR);
    }

    @Test
    @DisplayName("Test temp file copying")
    void testResourceFileCopy() {
        assertTrue(Files.exists(TEMP_FILE));
    }

    @Test
    @DisplayName("Test file last-X-lines loading")
    void testLastNumLinesReading() throws IOException {
        int lines = 500;
        final String file = FileReaderUtil.readLargeFile(TEMP_FILE, lines);
        assertEquals(lines, file.split("\n").length);
    }

    @Test
    @DisplayName("Test IP filtering")
    void testIpFiltering() {
        assertAll(
                () -> assertEquals( "<Censored IP>",
                        FileFilterUtil.filterLogs("79.21.112.166")),
                () -> assertEquals( "<Censored IP>:12345",
                        FileFilterUtil.filterLogs("79.21.112.166:12345")),
                () -> assertEquals( "Username (/<Censored IP>:12345) has disconnected",
                        FileFilterUtil.filterLogs("Username (/17.34.112.166:12345) has disconnected"))
        );
    }

}
