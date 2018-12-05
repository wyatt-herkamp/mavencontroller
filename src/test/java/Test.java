import me.kingtux.mavencontroller.Dependency;
import me.kingtux.mavencontroller.Repository;
import me.kingtux.mavencontroller.RepositoryFile;
import me.kingtux.simpleannotation.AnnotationFinder;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Test {
    public static void main(String[] args) throws Exception {
        System.out.println(System.getProperty("java.version"));
        Repository repository = Repository.of("http://repo.kingtux.me/repository/maven-public/", "kingtux-repo");
        Dependency dependency = Dependency.of("me.kingtux", "SimpleAnnotation", "1.0");
        Dependency.download(dependency);
        Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        method.setAccessible(true);
        method.invoke(Test.class.getClassLoader(), Dependency.getFile(dependency, RepositoryFile.JAR).toURI().toURL());
        AnnotationFinder.getMethodsWithAnnotation(Test.class,Override.class);
    }
}
