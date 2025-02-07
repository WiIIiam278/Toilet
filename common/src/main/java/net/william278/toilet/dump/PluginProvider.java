package net.william278.toilet.dump;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public interface PluginProvider extends DumpElementProvider {

    @NotNull
    @Unmodifiable
    List<PluginInfo> getPlugins();

}
