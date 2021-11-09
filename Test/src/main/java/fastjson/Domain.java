package fastjson;

public class Domain {
  private String name;
  private Integer status;
  public boolean isDone() {
    return status == 1;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
