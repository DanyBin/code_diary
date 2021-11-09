package process1.dispatcher;

import process1.datatypes.model.Graph;
import process1.datatypes.model.Node;
import process1.datatypes.model.RequestContext;
import process1.task.AbstractTask;
import process1.task.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TaskDispatcher {
  private Graph graph;

  public void call(int timeout, RequestContext context) {
    if (null != this.graph && !this.graph.getNodes().isEmpty() && null != context && timeout != 0) {
      //设置counter
      this.graph.setCounter(new CountDownLatch(this.graph.getNodes().size()));
      this.graph.setContext(build(context));

      //初始节点
      List<Node> firstSendList = new ArrayList<>();

      final List<Node> nodes = this.graph.getNodes();
      for (Node node : nodes) {
        node.getTask().setRequestContext(this.graph.getContext());
        if (node.getPreNodeMap().isEmpty()) {
          firstSendList.add(node);
        }
      }
      //按照weight排序
      Collections.sort(firstSendList);

      for (Node node : firstSendList) {
        node.getTask().sendRequest();
      }

      if (this.graph.getCounter().getCount() != 0) {
        try {
          //等待任务完成
          this.graph.getCounter().await(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
  private RequestContext build(RequestContext context) {
    if (null == context) {
      final RequestContext requestContext = new RequestContext();
      requestContext.setGraph(this.graph);
      return requestContext;
    } else {
      if (context.getContext() == null) {
        context.setContext(new HashMap<>());
      }
      context.setGraph(this.graph);
      return context;
    }
  }

  public void addTask(Task task) {
    Node node = new Node((AbstractTask) task);
    if (null != this.graph) {
      this.graph = new Graph();
    }
    this.graph.addNode(node);
  }

  public void relateTask(Task preTask,Task postTask) {
    final Node preNode = this.graph.getByName(preTask.getTaskName());
    final Node postNode = this.graph.getByName(postTask.getTaskName());
    this.graph.addEdge(preNode,postNode);
  }
}
