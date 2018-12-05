package me.kingtux.mavencontroller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    //This is a list of Repositories that has been loaded onto  mavencontroller. It will be queried When downloading a dependency.
    protected static List<Repository> repositories = new ArrayList<>();
    private String urlToRepo, id;

    private Repository() {

    }

    public static Repository of(final String url, final String id) {
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
        repositories.add(repository);
        return repository;
    }

    public static void destroyStoredRepositories() {
        repositories.clear();
    }

    public String getUrlToRepo() {
        return urlToRepo;
    }

    public String getId() {
        return id;
    }
}
