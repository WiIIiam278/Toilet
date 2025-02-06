package net.william278.toilet.dump;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class Dump {

    static final short CURRENT_SCHEMA_VERSION = 1;

    private short schemaVersion = Dump.CURRENT_SCHEMA_VERSION;
    private DumpMeta meta;
    private ServerInfo server;
    private EnvironmentInfo environment;
    private List<PluginInfo> plugins;
    private List<ConfigFile> configFiles;
    private String latestLog;

}
