package stocks;

/**
 * @author: XuQihang
 * @date: 2025/3/14 10:13
 * @description:
 */
public class Stocks {
    int maxProfitAllInOne(int max_k, int[] prices, int cooldown, int fee){
        int[][][] dp = new int[prices.length][max_k+1][2];
        //dp[-1][xx][0] = 0
        //dp[-1][xx][1] = MIN
        //dp[xx][0][0] = 0;
        //dp[xx][0][1] = MIN;
        for(int k = 0; k <= max_k; k++){
            dp[0][k][1] = -prices[0];
        }
        for(int i = 0; i < prices.length; i++){
            dp[i][0][0] = 0;
            dp[i][0][1] = Integer.MIN_VALUE;
        }
        for(int i = 1;i<prices.length;i++){
            for(int j = max_k;j>=1;j--){
                if(i<cooldown+1){
                    dp[i][j][0] = Math.max(dp[i-1][j][0],dp[i-1][j][1]+prices[i]-fee);
                    dp[i][j][1] = -prices[i];
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i-1][j][0],dp[i-1][j][1]+prices[i]-fee);
                dp[i][j][1] = Math.max(dp[i-1][j][1],dp[i-cooldown-1][j-1][0]-prices[i]);
            }
        }
        return dp[prices.length-1][max_k][0];
    }

    int maxProfitKInf(int[] prices, int cooldown, int fee){
        int[][] dp = new int[prices.length][2];
        //dp[-1][0] = 0
        //dp[-1][1] = MIN

        dp[0][1] = -prices[0];
        for(int i = 1;i<prices.length;i++){
            if(i<cooldown+1){
                dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]-fee);
                dp[i][1] = Math.max(dp[i-1][1],-prices[i]);
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]-fee);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-cooldown-1][0]-prices[i]);
        }
        return dp[prices.length-1][0];
    }
}
