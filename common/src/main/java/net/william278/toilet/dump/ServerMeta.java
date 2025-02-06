package net.william278.toilet.dump;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServerMeta {

    private String serverJarType;
    private String serverJarVersion;
    private String minecraftVersion;
    private boolean onlineMode;
    private ProxyState proxyState;

    private enum ProxyState {
        NO_PROXY,
        BEHIND_VELOCITY_PROXY,
        BEHIND_BUNGEECORD_PROXY,
        BEHIND_OTHER_PROXY,
        UNKNOWN
    }

}
