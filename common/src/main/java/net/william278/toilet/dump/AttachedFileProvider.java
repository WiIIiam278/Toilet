package net.william278.toilet.dump;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;
import java.util.Optional;

public interface AttachedFileProvider extends DumpElementProvider {

    @NotNull
    @Unmodifiable
    default List<AttachedFile> getAttachedFiles() {
        return getOptions().getFileInclusionRules().stream()
                .map(rule -> rule.getFileReader().read(rule.getFileMeta()))
                .flatMap(Optional::stream).toList();
    }

}
