package classLoader;

/**
 * @ClassName ClassLoaderTest
 * @Author bin
 * @Date 2021/3/24 下午7:49
 * @Decr TODO
 * @Link TODO
 **/
public class ClassLoaderTest extends ClassLoader{

    /**
     * 重写该方法即可
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String className = "className"; //类的完整名称
        byte[] data = new byte[1024];//类的数据大小
        return defineClass("className",data,0,data.length);
    }
}
