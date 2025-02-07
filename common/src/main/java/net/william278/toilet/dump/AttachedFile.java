package net.william278.toilet.dump;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class AttachedFile {

    private Type type;
    private String fileName;
    private String fileLabel;
    private String fileContents;

    public enum Type {
        CONFIG_FILE,
        DATABASE_FILE
    }
}
