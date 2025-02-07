package net.william278.toilet.file;

import net.william278.toilet.DumpOptions;
import net.william278.toilet.dump.AttachedFile;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

@FunctionalInterface
public interface FileReader {

    Optional<AttachedFile> read(@NotNull DumpOptions.FileInclusionRule.FileMeta meta);

}
