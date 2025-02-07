package net.william278.toilet.dump;

import org.jetbrains.annotations.NotNull;

public interface ServerMetaProvider extends DumpElementProvider {

    @NotNull
    ServerMeta getServerMeta();

}
