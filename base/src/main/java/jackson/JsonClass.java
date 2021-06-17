package jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @ClassName JsonClass
 * @Author bin
 * @Date 2021/4/23 上午10:34
 * @Decr TODO
 * @Link TODO
 **/

public class JsonClass {
  private int id;
  private double score;
  private String key;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public double getScore() {
    return score;
  }

  public void setScore(double score) {
    this.score = score;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public static void main(String[] args) throws JsonProcessingException {
    JsonClass jsonClass = new JsonClass();
    jsonClass.setId(1);
    jsonClass.setKey("key");
    jsonClass.setScore(1.0);

    ObjectMapper objectMapper = new ObjectMapper();
    System.out.println(objectMapper.writeValueAsString(jsonClass));;
  }
}
