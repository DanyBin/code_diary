import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ActionParam {
  private double weightAdjust;
//  private int fixLocation;
//  private boolean isFilter;
//  private boolean isSuppress;


  @Override
  public String toString() {
    return "ActionParam{" +
        "weightAdjust=" + weightAdjust +
        '}';
  }
}
