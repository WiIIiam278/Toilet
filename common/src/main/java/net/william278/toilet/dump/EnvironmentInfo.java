package net.william278.toilet.dump;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.lang.management.ManagementFactory;

@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnvironmentInfo {

    private String javaVersion = System.getProperty("java.version");
    private String javaVendor = System.getProperty("java.vendor");
    private String osName = System.getProperty("os.name");
    private long uptime = ManagementFactory.getRuntimeMXBean().getUptime();
    private long allocatedMemory = Runtime.getRuntime().totalMemory();
    private long freeMemory = Runtime.getRuntime().freeMemory();

}
