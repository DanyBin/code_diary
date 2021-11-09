package process1.task;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import process1.datatypes.model.Graph;
import process1.datatypes.model.Node;
import process1.datatypes.model.RequestContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
abstract
public class AbstractTask<T> implements Task<T> {
  //超时时间
  protected int timeout = 1000;

  @Getter
  //请求上下文
  private RequestContext requestContext;

  //任务别名
  private String alias;

  //用类名当作 任务名
  private String taskName = this.getClass().getSimpleName();

  @Getter
  //任务权重 - 用于排序
  public int weight;

  @Override
  public String getTaskName() {
    if (StringUtils.isNotEmpty(this.alias)) {
      return this.alias;
    }
    return StringUtils.isNotEmpty(this.taskName) ? this.taskName : this.getClass().getSimpleName();
  }

  @Override
  public boolean match() {
    return true;
  }

  @Override
  public void execute() {

  }

  @Override
  public boolean callback(T result) throws InterruptedException {
    return false;
  }

  @Override
  public void failed(Throwable e) {
  }

  /**
   * 获取当前节点 - 之后的所有节点
   * @return
   */
  public List<Task> getPostTasks() {
    final Node node = this.requestContext.getGraph().getByName(this.getTaskName());
    return node.getPostNodeMap().keySet().stream()
        .map(Node::getTask)
        .collect(Collectors.toList());
  }


  /**
   * 核心方法 - 用于实现业务方的请求
   */
  public abstract void sendRequest();

  public boolean markSuccess() {
    final Graph graph = this.requestContext.getGraph();
    final Node node = graph.getByName(this.getTaskName());
    return graph.markNodeSuccess(node);
  }

 public boolean markFailure() {
    final Graph graph = this.requestContext.getGraph();
    final Node node = graph.getByName(this.getTaskName());
    return graph.markNodeFail(node);
  }

  public boolean markInProcess() {
    final Graph graph = this.requestContext.getGraph();
    final Node node = graph.getByName(this.getTaskName());
    return graph.markNodeProcess(node);
  }

}
