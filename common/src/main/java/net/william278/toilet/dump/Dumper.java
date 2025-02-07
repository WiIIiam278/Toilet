package net.william278.toilet.dump;

import net.william278.toilet.DumpOptions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Dumper extends DumpMetaProvider, AttachedFileProvider, EnvironmentInfoProvider, PluginProvider, ProjectMetaProvider,
        ServerMetaProvider, LatestLogProvider {

    @NotNull
    default Dump createDump(@Nullable DumpUser dumpCreator) {
        return Dump.builder()
                .meta(dumpCreator == null ? getDumpMeta() : getDumpMeta(dumpCreator))
                .server(getServerMeta())
                .project(getProjectMeta())
                .environment(getEnvironmentInfo())
                .plugins(getPlugins())
                .files(getAttachedFiles())
                .latestLog(getLatestLog())
                .build();
    }

    @NotNull
    default Dump createDump() {
        return this.createDump(null);
    }

    @NotNull
    DumpOptions getOptions();

}
