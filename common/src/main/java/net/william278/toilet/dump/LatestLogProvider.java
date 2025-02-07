package net.william278.toilet.dump;

import org.jetbrains.annotations.NotNull;

public interface LatestLogProvider extends DumpElementProvider {

    @NotNull
    String getLatestLog();

}
