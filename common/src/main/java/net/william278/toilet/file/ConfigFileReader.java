package net.william278.toilet.file;

import net.william278.toilet.DumpOptions;
import net.william278.toilet.dump.AttachedFile;
import net.william278.toilet.dump.ConfigFile;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public final class ConfigFileReader implements FileReader {

    @Override
    public Optional<AttachedFile> read(@NotNull DumpOptions.FileInclusionRule.FileMeta meta) {
        try {
            final Path file = meta.getPath();
            if (Files.isRegularFile(file)) {
                return Optional.of(new ConfigFile(
                        file.getFileName().toString(),
                        meta.fileLabel(),
                        Files.readString(file, StandardCharsets.UTF_8)
                ));
            }
            return Optional.empty();
        } catch (IOException e) {
            return Optional.empty();
        }
    }

}
