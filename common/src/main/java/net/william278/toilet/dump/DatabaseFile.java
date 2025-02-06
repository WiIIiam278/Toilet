package net.william278.toilet.dump;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
public class DatabaseFile extends AttachedFile {

    public DatabaseFile(@NotNull String name, @NotNull String label, @NotNull String contents) {
        super(Type.DATABASE_FILE, name, label, contents);
    }

}
