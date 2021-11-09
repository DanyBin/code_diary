package process1.datatypes.model;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import process1.datatypes.consts.NodeStatusConstants;
import process1.task.AbstractTask;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Node implements Comparable{
  //节点的状态
  @Getter
  private AtomicInteger state = new AtomicInteger(0);

  //节点对应的要执行的任务
  @Getter
  @Setter
  private AbstractTask task;

  @Getter
  //前序节点 - Map
  private Map<Node,Relation> preNodeMap;
  @Getter
  //后续节点 - Map
  private Map<Node,Relation> postNodeMap;

  public Node(AbstractTask task) {
    this.task = task;
    preNodeMap = Maps.newHashMap();
    postNodeMap = Maps.newHashMap();
  }

  public String getName() {
    return this.task.getTaskName();
  }

  /**
   * 增加后续节点
   * @param node 节点
   * @param relation 节点依赖关系
   */
  void addPostNode(Node node,Relation relation) {
    this.postNodeMap.put(node,relation);
  }

  /**
   * 增加前序节点
   * @param node 节点
   * @param relation 节点依赖关系
   */
  void addPreNode(Node node,Relation relation) {
    this.preNodeMap.put(node,relation);
  }

  /**
   * 尝试执行节点
   */
  public void tryInvoke() {
    if (ready()) {
      //执行请求
      this.getTask().sendRequest();
    }
  }

  /**
   * 节点的依赖'
   * 是否已经准备就绪
   * @return
   */
  public boolean ready() {
    boolean ready = true;
    //查看前序节点
    if (this.preNodeMap.isEmpty()) {
      return ready;
    }

    for (Map.Entry<Node,Relation> entry : this.preNodeMap.entrySet()) {
      //前序节点
      final Node preNode = entry.getKey();
      //前序节点状态
      final Relation preRelation = entry.getValue();
      //判断前序节点是否已执行完成
      if (preNode.getState().get() != NodeStatusConstants.NOT_STARTED &&
          preNode.getState().get() !=  NodeStatusConstants.IN_PROCESS) {

        //当节点依赖关系成功，但没有节点状态没有成功.表示还没有执行成功
        if (preRelation == Relation.INVOKE_SUCCESS &&
        preNode.getState().get() != NodeStatusConstants.SUCCESS) {
          ready = false;
          break;
        }

        //1. 依赖状态不是失败 && preNode.Status == 2 时，
        //2. preNode执行执行
        if (preRelation != Relation.INVOKE_FAILURE ||
            preNode.getState().get() == NodeStatusConstants.FAILURE) {
          continue;
        }
        //当前节点执行失败
        ready = false;
        break;
      }

      //否则节点处于 -- 没有开始 || 进行中
      ready = false;
      break;
    }
    return  ready;
  }


  /**
   * 标记当前节点状态
   */
  boolean markStatus(int nodeStatus) {
    //节点处于-未开始 || 运行中
    if (this.getState().get() != NodeStatusConstants.SUCCESS &&
        this.getState().get() != NodeStatusConstants.FAILURE ) {
      //标记为-未开始. false
      if (nodeStatus == NodeStatusConstants.NOT_STARTED) {
        return false;
      } else if (nodeStatus == NodeStatusConstants.IN_PROCESS) {
        //将当前节点 - 由于未开始 -> 运行中
        return this.state.compareAndSet(NodeStatusConstants.NOT_STARTED,nodeStatus);
      } else {
        final int preStatus = this.state.getAndSet(nodeStatus);
        //这种情况。节点处于 -- 运行中的状态
        if (preStatus == nodeStatus) {
          return false;
        }

        //排序
        final Map<Node, Relation> sortMap = new TreeMap<>(this.postNodeMap);
        for (Map.Entry<Node,Relation> entry : sortMap.entrySet()) {
          //后续节点
          final Node postNode = entry.getKey();
          //后续节点- 标记自身状态
          postNode.markSelf();
          if (postNode.getState().get() != NodeStatusConstants.SUCCESS ||
          postNode.getState().get() != NodeStatusConstants.FAILURE) {
            postNode.tryInvoke();
          }
        }

        return true;
      }
    }
    return false;
  }

  /**
   * 标记自身的状态
   */
  private void markSelf() {
    //查看前序节点
    for (Map.Entry<Node,Relation> entry : this.preNodeMap.entrySet()) {
      final Node preNode = entry.getKey();
      final Relation preRelation = entry.getValue();
      //当 - 前序节点执行失败时. 标记当前节点也失败
      if (preNode.getState().get() == NodeStatusConstants.FAILURE
          && preRelation == Relation.INVOKE_SUCCESS) {
        this.getTask().getRequestContext().getGraph().markNodeFail(this);
        break;
      }
      //当 - 前序节点执行失败时。标记当前节点也失败
      if (preNode.getState().get() == NodeStatusConstants.SUCCESS
          && preRelation == Relation.INVOKE_FAILURE) {
        this.getTask().getRequestContext().getGraph().markNodeFail(this);
        break;
      }
    }
  }

  @Override
  public int compareTo(Object o) {
    if (this == o) {
      return 0;
    } else if (o == null) {
      return -1;
    } else {
      Node dest = (Node) o;
      return this.getTask().getWeight() > dest.getTask().getWeight() ? -1 : 1;
    }
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }
}
