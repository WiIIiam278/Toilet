package net.william278.toilet.file;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public interface ConfigDirectoryProvider {

    @NotNull
    Path getProjectConfigDirectory();

}
