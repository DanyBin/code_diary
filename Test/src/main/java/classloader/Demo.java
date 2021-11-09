package classloader;

public class Demo {
  public static void main(String[] args) throws ClassNotFoundException {
    //测试类加载器
    final Class<?> aClass = Class.forName("classloader.Demo");
    final ClassLoader classLoader = aClass.getClassLoader();
    System.out.println(classLoader);
  }

  //自定义类加载器
  static class CustomClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
      return super.findClass(name);
    }
  }
}
