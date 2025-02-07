package net.william278.toilet.dump;

import net.william278.toilet.DumpOptions;
import org.jetbrains.annotations.NotNull;

public interface DumpElementProvider {

    @NotNull
    DumpOptions getOptions();

}
