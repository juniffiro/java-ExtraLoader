package ua.juniffiro.extra.classpath;

import sun.misc.URLClassPath;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Stack;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 27/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public class ClasspathUtils {

    /*
    A utility for working with application
    Classpath on the fly.
     */

    /**
     * Get application Classpath.
     *
     * @return Application 'java.class.path'.
     */
    public static Collection<String> getClasspath() {
        String classPath = System.getProperty("java.class.path", ".");
        String[] split = classPath.split(File.pathSeparator);
        return new HashSet<>(Arrays.asList(split));
    }

    /**
     * Check if the file is loaded in Classpath.
     * @param path
     *        Path to the file you want to check
     */
    public static boolean checkInClasspath(String path) {
        return getClasspath().contains(path);
    }

    /**
     * Load the file into the Classpath
     * of a specific application "on the fly"
     * with URL.
     * @param url
     *        URL of the desired file
     * @param loader
     *        URLClassLoader
     * @throws Exception
     *         In the case of an error when loading.
     *         Can be related to either a URL error
     *         or an exception on the Reflection API side.
     */
    public static void addToPath(URL url, URLClassLoader loader) throws Exception {
        Method addUrl = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        addUrl.setAccessible(true);
        addUrl.invoke(loader, url);
        addUrl.setAccessible(false);
    }

    /**
     * Deleting a file from a certain Classpath
     * of a certain application "on the fly"
     * with URL.
     * @param loader
     *        URLClassLoader
     * @throws Exception
     *         In the case of an error when deleting.
     *         Can be related to either a URL error
     *         or an exception on the Reflection API side.
     */
    public static void removePath(URL url, URLClassLoader loader) throws Exception {
        Class<?> urlClass = URLClassLoader.class;
        Field ucpField = urlClass.getDeclaredField("ucp");
        ucpField.setAccessible(true);
        URLClassPath ucp = (URLClassPath) ucpField.get(loader);
        Class<?> ucpClass = URLClassPath.class;
        Field urlsField = ucpClass.getDeclaredField("urls");
        urlsField.setAccessible(true);
        Stack urls = (Stack) urlsField.get(ucp);
        urls.remove(url);
        urlsField.setAccessible(false);
        ucpField.setAccessible(false);
    }
}