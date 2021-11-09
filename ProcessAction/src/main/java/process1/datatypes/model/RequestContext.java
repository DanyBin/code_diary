package process1.datatypes.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Data
public class RequestContext {
  //上下文数据
  private Map<String,Object> context = Maps.newHashMap();
  //图
  private Graph graph;


  private String name = "DefaultName";

  //禁止的任务-不用执行
  private List<String> forbiddenTasks = Lists.newArrayList();

  /**
   * 从上下文的Context获取数据
   * @param key
   * @param defaultValue
   * @param <T>
   * @return
   */
  public <T> T getFromContext(String key,T defaultValue) {
    if (!this.context.isEmpty()) {
      return (T) this.context.getOrDefault(key,defaultValue);
    }
    return defaultValue;
  }

  public Map<String,Object> getContext() {
    if (this.context == null) {
      this.context = Maps.newHashMap();
    }
    return this.context;
  }
}
