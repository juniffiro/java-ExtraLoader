package ua.juniffiro.extra.classpath;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

import static ua.juniffiro.extra.classpath.ClasspathUtils.removePath;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 27/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public class URLClassloaderWrapper implements Extra {

    /*
    >_<

    Wrapper for modifying and managing the resources
    of the existing loader. Convenient to work with any loader.
     */

    private final Set<URL> urls;
    private final URLClassLoader urlClassLoader;

    public URLClassloaderWrapper(URLClassLoader urlClassLoader) {
        this.urls = new HashSet<>();
        this.urlClassLoader = urlClassLoader;
    }

    @Override
    public void add(URL url) throws Exception {
        ClasspathUtils.addToPath(url, urlClassLoader);
        urls.add(url);
    }

    @Override
    public void add(File file) throws Exception {
        add(file.toURI().toURL());
    }

    @Override
    public void add(String path) throws Exception {
        add(new File(path));
    }

    @Override
    public void remove(URL url) throws Exception {
        removePath(url, urlClassLoader);
        urls.remove(url);
    }

    @Override
    public void remove(File file) throws Exception {
        remove(file.toURI().toURL());
    }

    @Override
    public void remove(String path) throws Exception {
        remove(new File(path));
    }

    @Override
    public void removeAll() {
        urls.forEach(url -> {
            try {
                removePath(url, urlClassLoader);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public Set<URL> urls() {
        return urls;
    }

    @Override
    public int size() {
        return urlClassLoader.getURLs().length;
    }
}
