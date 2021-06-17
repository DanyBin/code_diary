package protocal.serializer;

/**
 * 序列号接口
 */
public interface Serializer {

  //JSON序列化
  byte JSON_SERIALIZER = 1;

  Serializer DEFAULT = new JSONSerializer();

  //序列化算法
  byte getSerializerAlgorithm();

  /**
   * Java对象转化成二进制
   * @param object
   * @return
   */
  byte[] serialize(Object object);

  /**
   * 二进制转化成Java对象
   * @param clazz
   * @param bytes
   * @param <T>
   * @return
   */
  <T> T deserialize(Class<T> clazz,byte[] bytes);
}
