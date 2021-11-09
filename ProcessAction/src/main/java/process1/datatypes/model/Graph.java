package process1.datatypes.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import process1.datatypes.consts.NodeStatusConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Data
public class Graph {

  private List<Node> nodes = new ArrayList();
  //记录整个图的执行进度
  private CountDownLatch counter;

  private RequestContext context;

  /**
   * 增加节点
   * @param node
   */
  public void addNode(Node node) {
    if (null != node) {
      this.nodes.add(node);
    }
  }

  /**
   * 增加边
   * @param preNode
   * @param postNode
   */
  public void addEdge(Node preNode,Node postNode) {
    addEdge(preNode,postNode,Relation.INVOKE_SUCCESS);
  }

  public void addEdge(Node preNode,Node postNode,Relation relation) {
    if (null != preNode && null != postNode) {
      if (preNode != postNode) {
        preNode.addPostNode(postNode,relation);
        postNode.addPreNode(preNode,relation);
      }
    }
  }

  /**
   * 根据名称获取Node。 一般都是className
   * @param name
   * @return
   */
  public Node getByName(String name) {
    if (StringUtils.isEmpty(name)) {
      return null;
    }
    return this.nodes.stream()
        .filter(node -> node.getName().equals(name))
        .findAny()
        .orElse(null);
  }

  /**
   * 更新节点状态
   * @param node
   * @param nodeStatus
   * @return
   */
  public boolean markNodeStatus(Node node,int nodeStatus) {
    boolean flag = node.markStatus(nodeStatus);
    if (flag && (nodeStatus == NodeStatusConstants.SUCCESS ||
        nodeStatus == NodeStatusConstants.FAILURE)) {
      this.counter.countDown();
    }
    return flag;
  }

  /**
   * 标记节点失败状态
   * @param node
   * @return
   */
  public boolean markNodeFail(Node node) {
    return markNodeStatus(node,NodeStatusConstants.FAILURE);
  }
  public boolean markNodeSuccess(Node node) {
    return this.markNodeStatus(node, NodeStatusConstants.SUCCESS);
  }


  public boolean markNodeProcess(Node node) {
    return this.markNodeStatus(node, NodeStatusConstants.IN_PROCESS);
  }
}
