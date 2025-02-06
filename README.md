<!--suppress ALL -->
<p align="center">
    <a href="https://github.com/WiIIiam278/Toilet/actions/workflows/ci.yml">
        <img src="https://img.shields.io/github/actions/workflow/status/WiIIiam278/Toilet/ci.yml?branch=master&logo=github"/>
    </a> 
    <a href="https://repo.william278.net/#/releases/net/william278/toilet/">
        <img src="https://repo.william278.net/api/badge/latest/releases/net/william278/toilet/toilet-common?color=00fb9a&name=Maven&prefix=v"/>
    </a> 
    <a href="https://discord.gg/tVYhJfyDWG">
        <img src="https://img.shields.io/discord/818135932103557162.svg?label=&logo=discord&logoColor=fff&color=7389D8&labelColor=6A7EC2" />
    </a> 
</p>
<br/>

**Toilet** is a cross-platform library for dumping plugin diagnostic information to the web.

## Setup
Toilet is available [on Maven](https://repo.william278.net/#/releases/net/william278/toilet/). You can browse the Javadocs [here](https://repo.william278.net/javadoc/releases/net/william278/toilet/latest).

<details>
<summary>Gradle setup instructions</summary> 

First, add the Maven repository to your `build.gradle` file:
```groovy
repositories {
    maven { url "https://repo.william278.net/releases" }
}
```

Then, add the dependency itself. Replace `VERSION` with the latest release version. (e.g., `1.2.1`) and `PLATFORM` with the platform you are targeting (e.g., `bukkit`). If you want to target pre-release "snapshot" versions (not recommended), you should use the `/snapshots` repository instead.

```groovy
dependencies {
    implementation "net.william278.toilet:toilet-PLATFORM:VERSION"
}
```
</details>

Using Maven/something else? There's instructions on how to include Toilet on [the repo browser](https://repo.william278.net/#/releases/net/william278/toilet).

## Building
To build Toilet, run `clean build` in the root directory. The output JARs will be in `target/`.

## License
Toilet is licensed under the Apache License 2.0. See [LICENSE](https://github.com/WiIIiam278/Toilet/raw/master/LICENSE) for more information.