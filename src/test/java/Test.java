import me.kingtux.mavencontroller.Dependency;
import me.kingtux.mavencontroller.Repository;
import me.kingtux.mavencontroller.RepositoryFile;

import java.io.File;

public class Test {
    public static void main(String[] args) throws Exception {
        //This is an example and test
        System.out.println("Java Version " + System.getProperty("java.version"));
        Repository repository = Repository.of("http://repo.kingtux.me/repository/maven-public/", "kingtux-repo");
        Dependency dependency = Dependency.of("me.kingtux", "SimpleAnnotation", "1.0");
        Dependency.download(dependency);
        Dependency.download(DependAccessorExample.GSON);
        File file = Dependency.getFile(DependAccessorExample.GSON, RepositoryFile.JAR);
    }
}
