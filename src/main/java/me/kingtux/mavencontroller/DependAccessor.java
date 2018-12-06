package me.kingtux.mavencontroller;

/**
 * See DependAccessorExample for how to use
 * @author KingTux
 */
public interface DependAccessor {
    /**
     * The depend
     * @return the dependency
     */
    Dependency getDepend();

    /**
     * The repository the depend is on
     * @return the repoistory
     */
    Repository getRepository();
}
