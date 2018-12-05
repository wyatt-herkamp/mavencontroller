import me.kingtux.mavencontroller.Dependency;
import me.kingtux.mavencontroller.Repository;

public class Test {
    public static void main(String[] args) throws Exception {
        System.out.println(System.getProperty("java.version"));
        Repository repository = Repository.of("http://repo.kingtux.me/repository/maven-public/", "kingtux-repo");
        Dependency dependency = Dependency.of("me.kingtux", "SimpleAnnotation", "1.0");
        Dependency.download(dependency);
    }
}
