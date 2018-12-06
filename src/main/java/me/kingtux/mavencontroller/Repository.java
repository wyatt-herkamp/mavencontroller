package me.kingtux.mavencontroller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * This represents a Repository
 *
 * @author KingTux
 */
public class Repository {
    //This is a list of Repositories that has been loaded onto  mavencontroller. It will be queried When downloading a dependency.
    protected static List<Repository> repositories;
    public static final Repository JITPACK, CENTRAL;

    static {
        repositories = new ArrayList<>();
        JITPACK = Repository.of("https://jitpack.io", "jitpack.io");
        CENTRAL = Repository.of("https://repo.maven.apache.org/maven2/", "maven");
    }

    private String urlToRepo, id;

    private Repository() {

    }
    /**
     * Creates a repo
     *
     * @param url the url
     * @param id  id of repo
     * @return the repository object
     */
    public static Repository of(final String url, final String id) {
        return of(url, id, true);
    }

    /**
     * Creates a repo
     *
     * @param url the url
     * @param id  id of repo
     * @param save To save the repository in memory
     * @return the repository object
     */
    public static Repository of(final String url, final String id, boolean save) {
        try {
            URL url1 = new URL(url);
            if (!SimpleUtils.isSiteOnline(url1)) {
                throw new Exception("Site not available or does not exist!");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(url + " Did not pass tests", e);
        }

        Repository repository = new Repository();
        repository.urlToRepo = SimpleUtils.fixWebsiteURL(url);
        repository.id = id;
        if (save) {
            repositories.add(repository);
        }
        return repository;
    }

    /**
     * Destroys all cached repos
     */
    public static void destroyStoredRepositories() {
        repositories.clear();
    }


    /**
     * grabs the url to repo
     *
     * @return the url to repo
     */
    public String getUrlToRepo() {
        return urlToRepo;
    }

    /**
     * repo id
     *
     * @return the repo id
     */
    public String getId() {
        return id;
    }
}
