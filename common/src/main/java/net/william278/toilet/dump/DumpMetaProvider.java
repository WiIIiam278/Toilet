package net.william278.toilet.dump;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;

public interface DumpMetaProvider extends DumpElementProvider {

    @NotNull
    default DumpMeta getDumpMeta(@Nullable DumpUser dumpCreator) {
        return new DumpMeta(Instant.now(), dumpCreator);
    }

    @NotNull
    default DumpMeta getDumpMeta() {
        return getDumpMeta(null);
    }

}
