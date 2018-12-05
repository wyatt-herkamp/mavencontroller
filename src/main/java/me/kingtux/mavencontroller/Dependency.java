package me.kingtux.mavencontroller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This represents a depend
 *
 * @author KingTux
 */
public class Dependency {

    private String groupId, artifactId, version;
    private boolean installedOnLocal = false;

    private Dependency() {

    }

    public URL getURL() {
        try {
            return new URL(getURLPath());
        } catch (MalformedURLException e) {
            return null;
        }
    }

    public String getSystemPath() {
        return groupId.replace(".", File.separator) + File.separator + artifactId + File.separator + version;
    }

    public String getURLPath() {
        return groupId.replace(".", "/") + "/" + artifactId + "/" + version;
    }

    public static Dependency of(String groupId, String artifactId, String version) {
        Dependency dependency = new Dependency();
        dependency.groupId = groupId;
        dependency.artifactId = artifactId;
        dependency.version = version;
        dependency.installedOnLocal = isInstalledOnLocal(dependency);
        System.out.println(dependency.installedOnLocal);
        return dependency;
    }

    public static boolean download(Dependency dependency, Repository repository) {

        String url = repository.getUrlToRepo();
        if (!url.endsWith("/")) {
            url += "/";
        }
        url += dependency.getURLPath() + "/";
        for (RepositoryFile repositoryFile : RepositoryFile.values()) {
            new File(SimpleUtils.getPathToLocalRepo() + File.separator + dependency.getSystemPath()).mkdirs();
            try {
                SimpleUtils.download(url + repositoryFile.parse(dependency.artifactId, dependency.version), SimpleUtils.getPathToLocalRepo() + File.separator + dependency.getSystemPath() + File.separator + repositoryFile.parse(dependency.artifactId, dependency.version));
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    public static boolean download(Dependency dependency) {

        for (Repository repository : Repository.repositories) {
            if (download(dependency, repository)) {
                return true;
            }
        }
        return false;
    }

    public static File getFile(Dependency dependency, RepositoryFile repositoryFile) {
        return new File(new File(new File(SimpleUtils.getPathToLocalRepo()), dependency.getSystemPath()), repositoryFile.parse(dependency.artifactId, dependency.version));
    }

    public static boolean isInstalledOnLocal(Dependency dependency) {
        return getFile(dependency, RepositoryFile.POM).exists()&& getFile(dependency, RepositoryFile.JAR).exists();
    }
}
