package net.william278.toilet.file;

import net.william278.toilet.DumpOptions;
import net.william278.toilet.dump.AttachedFile;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public final class DatabaseFileReader implements FileReader {

    @Override
    public Optional<AttachedFile> read(@NotNull DumpOptions.FileInclusionRule.FileMeta label) {
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
