/**
 * 题目描述：一次股票交易包含买入和卖出，只进行一次交易，求最大收益。
 *
 * 只要记录前面的最小价格，将这个最小价格作为买入价格，然后将当前的价格作为售出价格，查看当前收益是不是最大收益。
 */
public class Code_15_BestTimeToBuyAndSellStock {
  public int maxProfit(int[] prices) {
    if (prices.length == 0) {
      return 0;
    }
    //动态规划的解决方式
    int min = prices[0];
    int max = 0;
    for (int i = 1; i < prices.length ; i++) {
      if (min > prices[i]) {
        //更新变量
        min = prices[i];
      } else {
        //更新变量
        max = Math.max(max,prices[i]-min);
      }
    }
    return max;
  }
}
