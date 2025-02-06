package net.william278.toilet;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Toilet {

    private ToiletOptions plumbing;

    public final void dump() {

    }

    public abstract void readConfigFiles();

}
