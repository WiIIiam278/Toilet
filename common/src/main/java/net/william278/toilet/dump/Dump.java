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
    private ServerMeta server;
    private ProjectMeta project;
    private EnvironmentInfo environment;
    private List<PluginInfo> plugins;
    private List<AttachedFile> files;
    private String latestLog;

}
