import com.google.common.collect.Maps;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *
 * 给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
 *
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是无效IP地址。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/restore-ip-addresses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 *
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 *
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 */
public class Code_40_RestoreIPAddresses {
  public List<String> restoreIpAddresses(String digits) {
    if (null == digits || digits.trim().length() == 0) {
      return Collections.emptyList();
    }
    List<String> ret = new ArrayList<>();
    dfsSort(0,new StringBuffer(),ret,digits);
    return ret;
  }

  /**
   * 回溯
   */
  public void dfsSort(int k,StringBuffer buffer,List<String> ret,String digits){
    //终止条件 == 4等分 & 长度为空
    if (k == 4 || digits.length() == 0) {
      if (k == 4 && digits.length() == 0) {
        ret.add(buffer.toString());
      }
      return;
    }

    //限制最大数量为3个元素
    for (int i = 0; i < digits.length() && i <= 2 ; i ++) {
      //防止前一元素为0
      if (i != 0 && digits.charAt(i) == '0') {
        break;
      }
      String substring = digits.substring(0, i + 1);
      //必须小于/等于255
      if (Integer.valueOf(substring) <= 255) {
        //除去第一个元素
        if (buffer.length() != 0) {
          substring = "." + substring;
        }
        buffer.append(substring);
        dfsSort(k+1,buffer,ret,digits);
        //删除元素
        buffer.delete(buffer.length() - substring.length(),buffer.length());
      }
    }
  }
}
