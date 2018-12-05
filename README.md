#Maven Controller
Maven Controller is a open source Maven Depend grabber. This library will allow you to grab the jar file from online and install to your local repository or if it already present will just return the path.

An extension will be made that adds it to your ClassLoader specified or the default one.


Is Ready: FALSE

## Maven
```xml
   <repository>
      <id>kingtux-repo</id>
      <url>http://repo.kingtux.me/repository/maven-public/</url>
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
  maven { url 'http://repo.kingtux.me/repository/maven-public/' }
}

dependencies {
   compile "me.kingtux:mavencontroller:1.0-SNAPSHOT"
}
```