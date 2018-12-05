import me.kingtux.mavencontroller.DependAccessor;
import me.kingtux.mavencontroller.Dependency;
import me.kingtux.mavencontroller.Repository;

public enum DependAccessorExample implements DependAccessor {
    GSON(Dependency.of("com.google.code.gson","gson","2.8.5"), Repository.CENTRAL);
    DependAccessorExample(Dependency dependency, Repository repository) {
        this.dependency = dependency;
        this.repository = repository;
    }

    private Dependency dependency;
    private Repository repository;

    @Override
    public Dependency getDepend() {
        return dependency;
    }

    @Override
    public Repository getRepository() {
        return repository;
    }
}
