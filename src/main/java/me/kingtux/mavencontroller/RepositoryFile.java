package me.kingtux.mavencontroller;

public enum RepositoryFile {
    JAR("%1$s-%2$s.jar"),
    POM("%1$s-%2$s.pom"),
    JAVADOC("%1$s-%2$s-javadoc.jar"),
    SOURCE("%1$s-2$s-sources.jar");
    private String fileName;

    RepositoryFile(String fileName) {
        this.fileName = fileName;
    }

    public String parse(String name, String version) {
        return String.format(fileName, name, version);
    }
}
