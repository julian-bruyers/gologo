<!--  README badges  -->
<a href="#"><img src="https://img.shields.io/github/v/release/julian-bruyers/gologo?label=Latest&labelColor=2D3748&color=003087"></a>
<a href="#"><img src="https://img.shields.io/github/license/julian-bruyers/gologo?&label=License&logo=opensourceinitiative&logoColor=ffffff&labelColor=2D3748&color=2D3748"></a>
<a href="https://plugins.jetbrains.com/plugin/27510-gologo"><img src="https://img.shields.io/badge/Linux-E95420?logo=linux&logoColor=white" align="right"></a>
<a href="https://plugins.jetbrains.com/plugin/27510-gologo"><img src="https://custom-icon-badges.demolab.com/badge/Windows-0078D6?logo=windows11&logoColor=white" align="right"></a>
<a href="https://plugins.jetbrains.com/plugin/27510-gologo"><img src="https://img.shields.io/badge/macOS-333333?logo=apple&logoColor=F0F0F0" align="right"></a>



![GoLogo Plugin](/docs/assets/gologo_header.png)


## About GoLogo

GoLogo is a plugin for the JetBrains IDE [GoLand](https://www.jetbrains.com/de-de/go/). This plugin replaces the
beloved GoLang gopher icon in the file explorer and editor tabs (which is the default with the JetBrains
[Go plugin](https://plugins.jetbrains.com/plugin/9568-go)) with the official GoLang logo icon.

<p align="center">
  <a href="https://plugins.jetbrains.com/plugin/27510-gologo">
    <img src="https://img.shields.io/badge/JetBrains%20Marketplace-Install%20GoLogo-blue?style=for-the-badge&logo=jetbrains" alt="Install GoLogo from JetBrains Marketplace">
  </a>
</p>

## How to build

### 1. Clone the repository

```bash
git clone https://github.com/Julian-Bruyers/gologo
```

### 2. Make sure you have [Gradle](https://gradle.org) and at least [Java JDK v17](https://www.oracle.com/de/java/technologies/downloads/) installed

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

### 3. Navigate to the repository and run gradlew

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

### 4. The plugin `GoLogo-X.Y.Z.zip` will be built in the `/build/distributions` folder

## License and Copyright

The GoLogo plugin is licensed under the [MIT License](LICENSE).

The icons used in this plugin are derived from resources on the official [go.dev](https://go.dev) website. Please refer to the [Go Brand Guidelines](https://go.dev/brand) and [Go Terms of Service](https://go.dev/tos) for information regarding the use of Go's brand assets.

Compatibility with JetBrains Marketplace: This plugin is intended for distribution via the JetBrains Marketplace. While this plugin utilizes its own MIT License, users and developers should also be aware of the terms and conditions set forth by JetBrains for plugins hosted on their marketplace. It is recommended to review the [JetBrains Marketplace Agreement](https://www.jetbrains.com/legal/docs/marketplace/marketplace-agreement/) for further details.

**GoLogo is not affiliated with the Go brand, Google, or the Go language development team.**