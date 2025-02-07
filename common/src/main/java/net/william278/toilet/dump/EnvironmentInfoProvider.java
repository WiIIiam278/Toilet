package net.william278.toilet.dump;

import org.jetbrains.annotations.NotNull;

public interface EnvironmentInfoProvider extends DumpElementProvider {

    @NotNull
    default EnvironmentInfo getEnvironmentInfo() {
        return new EnvironmentInfo();
    }

}
