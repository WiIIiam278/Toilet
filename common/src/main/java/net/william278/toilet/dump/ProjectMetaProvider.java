package net.william278.toilet.dump;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public interface ProjectMetaProvider extends DumpElementProvider {

    @NotNull
    ProjectMeta getProjectMeta();

}
