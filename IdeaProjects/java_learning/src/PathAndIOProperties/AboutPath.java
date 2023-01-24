package PathAndIOProperties;

public class AboutPath {
    /*
    * Usually, we use relative path to access file in IDEA,
    * but the portability of accessing files in this way is poor.
    *
    * The way to solve this problem is using class path.
    * What is the class path? The file in the directory "src"
    *
    * */
    public static void main(String[] args) {
        /*
        * 1.Thread.currentThread() return current thread
        * 2.getContextClassLoader() return ClassLoader, the method of current thread object
        * 3.getResource() get resource from the class path, the method of ClassLoader
        * 4.getPath() return absolute path of resource, the method of Url
        *
        * The following code can be used to get the path automatically,
        * so it can be used to any system
        * */
        String path = Thread.currentThread().getContextClassLoader().getResource("UserInformation.properties").getPath();
        /*
        * Get resource file from package
        * */
        String resource = Thread.currentThread().getContextClassLoader().getResource("PathAndIOProperties/resource.properties").getPath();

        System.out.println(path);
        System.out.println(resource);
    }
}
