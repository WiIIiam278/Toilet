package net.william278.toilet.dump;

import lombok.*;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DumpMeta {

    private Instant timestamp = Instant.now();

    private @Nullable DumpUser creator;

}
