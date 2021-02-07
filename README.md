# Maven Controller

# Project no longer supported. Please use [mavenlibrary](https://github.com/wherkamp/mavenlibrary) 
Maven Controller is a open source Maven Depend grabber. This library will allow you to grab the jar file from online and install to your local repository or if it already present will just return the path.

An extension will be made that adds it to your ClassLoader specified or the default one.

Please Use
Is Ready: READY FOR TESTING

### Planned Features 
* Read maven-metadata.xml to get latest version

## Maven
```xml
   <repository>
      <id>kingtux-repo</id>
      <url>https://repo.kingtux.me/storages/maven/kingtux-repo</url>
    </repository>
    
    <dependency>
      <groupId>me.kingtux</groupId>
      <artifactId>mavencontroller</artifactId>
      <!---Make sure you use Latest Version!-->
      <version>1.0-SNAPSHOT/version>
      <scope>compile</scope>
    </dependency>
```
## Gradle
```
repositories {
  maven { url 'https://repo.kingtux.me/storages/maven/kingtux-repo' }
}

dependencies {
   compile "me.kingtux:mavencontroller:1.0-SNAPSHOT"
}
```
