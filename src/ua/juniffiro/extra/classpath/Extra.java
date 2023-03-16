package ua.juniffiro.extra.classpath;

import java.io.File;
import java.net.URL;
import java.util.Set;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 27/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public interface Extra {

    /**
     * Add file to a specific Classpath.
     *
     * @param url
     *        Path to the file you want to add using the URL
     * @throws Exception
     *         In the case of an error when adding.
     *         Can be caused either by a URL error
     *         or an exception on the implementation side.
     */
    void add(URL url) throws Exception;

    /**
     * Add file to a specific Classpath.
     *
     * @param file
     *        The file you want to add
     * @throws Exception
     *         In the case of an error when adding.
     *         Can be caused either by a URL error
     *         or an exception on the implementation side.
     */
    void add(File file) throws Exception;

    /**
     * Add file to a specific Classpath.
     *
     * @param pathToFile
     *        Path to the file you want to add
     * @throws Exception
     *         In the case of an error when adding.
     *         Can be caused either by a URL error
     *         or an exception on the implementation side.
     */
    void add(String pathToFile) throws Exception;

    /**
     * Remove file from a specific Classpath.
     *
     * @param url
     *        Path to the file you want to remove using the URL
     * @throws Exception
     *         In the case of an error when adding.
     *         Can be caused either by a URL error
     *         or an exception on the implementation side.
     */
    void remove(URL url) throws Exception;

    /**
     * Remove file from a specific Classpath.
     *
     * @param file
     *        The file you want to remove
     * @throws Exception
     *         In the case of an error when adding.
     *         Can be caused either by a URL error
     *         or an exception on the implementation side.
     */
    void remove(File file) throws Exception;

    /**
     * Remove file from a specific Classpath.
     *
     * @param pathToFile
     *        Path to the file you want to remove
     * @throws Exception
     *         In the case of an error when adding.
     *         Can be caused either by a URL error
     *         or an exception on the implementation side.
     */
    void remove(String pathToFile) throws Exception;

    /**
     * Delete all the files you added
     * from the Classpath.
     */
    void removeAll();

    /**
     * Set of links to files that have been
     * added to Classpath.
     */
    Set<URL> urls();

    /**
     * Count of files uploaded to the Classpath.
     */
    int size();
}
