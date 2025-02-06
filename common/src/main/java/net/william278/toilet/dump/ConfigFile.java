package net.william278.toilet.dump;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
public class ConfigFile extends AttachedFile {

    public ConfigFile(@NotNull String name, @NotNull String label, @NotNull String contents) {
        super(Type.CONFIG_FILE, name, label, contents);
    }

}
