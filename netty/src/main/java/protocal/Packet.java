package protocal;

import com.alibaba.fastjson.annotation.JSONField;
import io.netty.buffer.ByteBuf;
import lombok.Data;

/**
 * Java对象
 */
@Data
public abstract class Packet {
  /**
   * 协议版本
   */
  @JSONField(deserialize = false, serialize = false)
  private Byte version = 1;

  /**
   * 指令
   * @return
   */
  @JSONField(serialize = false)
  public abstract Byte getCommand();
}
