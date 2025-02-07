package net.william278.toilet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.william278.toilet.dump.Dump;
import net.william278.toilet.dump.Dumper;
import net.william278.toilet.dump.DumpUser;
import org.jetbrains.annotations.Nullable;

import java.net.URI;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Toilet implements Dumper {

    private DumpOptions options;

    public final URI dump(@Nullable DumpUser dumper) {
        final Dump dump = createDump(dumper);
        final String json = createGson().toJson(dump);
        return null; // todo return URL of dump
    }

    public final URI dump() {
        return this.dump(null);
    }

    private Gson createGson() {
        return new GsonBuilder().create();
    }

}
