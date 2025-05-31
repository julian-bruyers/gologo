# GoLogo

![GoLogo Plugin](/docs/assets/gologo_header.png)

## About GoLogo

GoLogo is a plugin for the JetBrains IDE [GoLand](https://www.jetbrains.com/de-de/go/). This plugin replaces the
beloved GoLang gopher icon in the file explorer and editor which is the standard with the JetBrains
[Go plugin](https://plugins.jetbrains.com/plugin/9568-go) to the GoLang icon.

## How to build

### 1. Clone the repository

```bash
git clone https://github.com/Julian-Bruyers/gologo
```

### 2. Make sure to have [Gradle](https://gradle.org) and min. [Java JDK v17](https://www.oracle.com/de/java/technologies/downloads/) installed

Windows

```cmd
choco install openjdk --version=17.0.2
choco install gradle
```

macOS

```bash
brew install openjdk@17
brew install gradle
```

Linux

```bash
sudo apt update
sudo apt install openjdk-17-jdk
```

```bash
sudo apt install unzip wget -y
wget https://services.gradle.org/distributions/gradle-8.13-bin.zip -P /tmp
sudo unzip -d /opt/gradle /tmp/gradle-8.13-bin.zip
echo 'export PATH=$PATH:/opt/gradle/gradle-8.13/bin' >> ~/.bashrc
source ~/.bashrc
```

### 3. Go into the repository and run the gradlew

macOS

```bash
cd gologo
sudo chmod +x gradlew
./gradlew build
./gradlew buildPlugin
```

Windows

```cmd
cd gologo
.\gradlew.bat build
.\gradlew.bat buildPlugin
```

Linux

```bash
cd gologo
sudo chmod +x gradlew
./gradlew build
./gradlew buildPlugin
```

### 4. The plugin `GoLogo-X.Y.Z.zip` is built in the `/build/distributions` folder

## License and Copyright

The GoLogo plugin is licensed under the [MIT License](LICENSE).

The icons used in this plugin are derived from resources on the official [go.dev](https://go.dev) website. Please refer to the [Go Brand Guidelines](https://go.dev/brand) and [Go Terms of Service](https://go.dev/tos) for information regarding the use of Go's brand assets.

Compatibility with JetBrains Marketplace: This plugin is intended for distribution via the JetBrains Marketplace. While this plugin utilizes its own MIT License, users and developers should also be aware of the terms and conditions set forth by JetBrains for plugins hosted on their marketplace. It is recommended to review the [JetBrains Marketplace Agreement](https://www.jetbrains.com/legal/docs/marketplace/marketplace-agreement/) for further details.

**GoLogo is not affiliated with the Go Brand, Google or the Go language development team**