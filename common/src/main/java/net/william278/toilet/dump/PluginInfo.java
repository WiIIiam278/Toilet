package net.william278.toilet.dump;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class PluginInfo {

    private String name;
    private String version;
    private String description;
    private List<String> authors;
    private boolean enabled;
    private List<Label> labels;

    @NoArgsConstructor
    @AllArgsConstructor
    public final class Label {
        private String name;
        private String color;
    }

}
