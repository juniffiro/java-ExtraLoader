package ua.juniffiro.extra.classpath;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;
import java.util.HashSet;
import java.util.Set;

import static ua.juniffiro.extra.classpath.ClasspathUtils.removePath;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 27/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public class ExtraURLClassloader extends URLClassLoader implements Extra {

    /*

    >_<

    Used to create your own new loaders in the application.
     */

    private final Set<URL> sUrls = new HashSet<>();

    public ExtraURLClassloader() {
        super(new URL[0]);
    }

    public ExtraURLClassloader(URL[] urls, ClassLoader classLoader) {
        super(urls, classLoader);
    }

    public ExtraURLClassloader(URL[] urls) {
        super(urls);
    }

    public ExtraURLClassloader(URL[] urls, ClassLoader classLoader, URLStreamHandlerFactory urlStreamHandlerFactory) {
        super(urls, classLoader, urlStreamHandlerFactory);
    }

    @Override
    public void add(URL url) {
        addURL(url);
        sUrls.add(url);
    }

    @Override
    public void add(File file) throws MalformedURLException {
        add(file.toURI().toURL());
    }

    @Override
    public void add(String path) throws MalformedURLException {
        add(new File(path));
    }

    @Override
    public void remove(URL url) throws Exception {
        removePath(url, this);
        sUrls.remove(url);
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
        sUrls.forEach(url -> {
            try {
                removePath(url, this);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public Set<URL> urls() {
        return sUrls;
    }

    @Override
    public int size() {
        return getURLs().length;
    }
}
