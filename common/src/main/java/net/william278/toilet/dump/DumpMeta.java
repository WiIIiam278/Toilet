package net.william278.toilet.dump;

import lombok.*;

import java.time.Instant;

@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DumpMeta {

    private Instant timestamp = Instant.now();

    private User creator;

}
