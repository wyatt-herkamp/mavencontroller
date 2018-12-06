package me.kingtux.mavencontroller;

import java.io.File;

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

    /**
     * Gets the path to the file that would be under the local repository
     *
     * @return the path under .m2/repository/
     */
    public String getSystemPath() {
        return groupId.replace(".", File.separator) + File.separator + artifactId + File.separator + version;
    }

    /**
     * The path that it would be online
     *
     * @return The path that it would be online so REPO/THIS
     */
    public String getURLPath() {
        return groupId.replace(".", "/") + "/" + artifactId + "/" + version;
    }

    /**
     * Creates an Dependency Object
     *
     * @param groupId    the group id of the the depend
     * @param artifactId the artifactId of the depend
     * @param version    version
     * @return the depend object
     */
    public static Dependency of(String groupId, String artifactId, String version) {
        Dependency dependency = new Dependency();
        dependency.groupId = groupId;
        dependency.artifactId = artifactId;
        dependency.version = version;
        dependency.installedOnLocal = isInstalledOnLocal(dependency);
        System.out.println(dependency.installedOnLocal);
        return dependency;
    }

    /**
     * Downloads the depend with the object
     *
     * @param dependency
     * @param repository
     * @return
     */
    public static boolean download(Dependency dependency, Repository repository) {

        String url = repository.getUrlToRepo();
        if (!url.endsWith("/")) {
            url += "/";
        }
        url += dependency.getURLPath() + "/";
        //Just throw it out if the main jar is missing;
        SimpleHTTPClient httpClient = SimpleHTTPClient.create(url + RepositoryFile.JAR.parse(dependency.artifactId, dependency.version));
        if (httpClient.getResponseCode() != 200) {
            return false;
        }
        for (RepositoryFile repositoryFile : RepositoryFile.values()) {
            new File(SimpleUtils.getPathToLocalRepo() + dependency.getSystemPath()).mkdirs();
            SimpleHTTPClient repoFile = SimpleHTTPClient.create(url + repositoryFile.parse(dependency.artifactId, dependency.version));
            if (repoFile.getResponseCode() != 200) {
                continue;
            }
            repoFile.download(new File(SimpleUtils.getPathToLocalRepo() + dependency.getSystemPath() + File.separator + repositoryFile.parse(dependency.artifactId, dependency.version)));
        }
        return true;
    }

    /**
     * Downloads a depend from accessor
     *
     * @param dependAccessor the accessor to download from
     * @return did it work
     */
    public static boolean download(DependAccessor dependAccessor) {
        return download(dependAccessor.getDepend(), dependAccessor.getRepository());
    }

    /**
     * Tries to download with cached repos
     *
     * @param dependency Depend to download
     * @return if it worked
     */
    public static boolean download(Dependency dependency) {

        for (Repository repository : Repository.repositories) {
            if (download(dependency, repository)) {
                return true;
            }
        }
        return false;
    }

    /**
     * The file
     *
     * @param dependency     depend to locate
     * @param repositoryFile the file type
     * @return the file
     */
    public static File getFile(Dependency dependency, RepositoryFile repositoryFile) {
        return new File(new File(new File(SimpleUtils.getPathToLocalRepo()), dependency.getSystemPath()), repositoryFile.parse(dependency.artifactId, dependency.version));
    }
    /**
     * The file
     *
     * @param dependency     depend to locate
     * @param repositoryFile the file type
     * @return the file
     */
    public static File getFile(DependAccessor dependency, RepositoryFile repositoryFile) {
        return getFile(dependency.getDepend(), repositoryFile);
    }

    /**
     * is it installed on the local repo
     *
     * @param dependency the depend to check
     * @return is it installed
     */
    public static boolean isInstalledOnLocal(Dependency dependency) {
        return getFile(dependency, RepositoryFile.POM).exists() && getFile(dependency, RepositoryFile.JAR).exists();
    }
}
