package classloader;

public class ThreadClassLoaderTest {

  public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    final ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
    final Class<?> aClass = contextClassLoader.loadClass(T.class.getName());
    final T o = (T)aClass.newInstance();
    o.print();
  }
  public static class T {
    private static final String pushTemplate;
    static {
      pushTemplate ="11";
    }
    public void print() {
      System.out.println(pushTemplate);
    }
  }
}
