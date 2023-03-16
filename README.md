# URLExtraLoader
## or Java extra classpath

URLExtraLoader is a utility that allows you to quickly and easily create a URLClassLoader or work with an existing instance.
Functions:
1) Create your own URLClassLoader with dynamic modification of Classpath on the fly and control of resources loaded into Classpath.
2) Extension of the standard URLClassLoader with a wrapper.
3) A utility to work on the fly. It is sufficient to pass a reference to the loader object.

Default way to work with URLClassLoader.
In this way, we cannot add files to Classpath.
```java
URLClassLoader classLoader = new URLClassLoader(new URL[]{
        new URL("file:\\test.jar")
});
```

**URLExtraClassLoader**
```java
Extra extra = new ExtraURLClassloader(new URL[]{
        new URL("file:\\test.jar")
});
```
We can now add and remove files to Classpath dynamically.
```java
extra.add(new File("test.jar"));
```

Example of using the **URLClassLoader** wrapper
```java
Extra extra = new URLClassloaderWrapper(new URLClassLoader(new URL[0]));
extra.add(new File("test.jar"));
```

You can also use **ClasspathUtils**. <br/> Let's create a new instance of URLClassLoader and add a test jar file to Classpath.
```java
URLClassLoader urlClassLoader = new URLClassLoader(new URL[0]);
ClasspathUtils.addToPath(new URL("file:\\test.jar"), urlClassLoader);
```

## Open source

URLExtraLoader is an open source project distributed under the Apache License 2.0 <br>

## Getting started

1. Download the latest build from releases
2. Read the FAQ and examples
3. Enjoy!

## Status

The project is in beta. Use at your own risk. <br>

