import java.util.HashMap;
import java.util.Map;

public class LongestSubarraySumBoth {
    public static int bruteforce(int[] nums, int k){
        int n = nums.length;
        int len = 0;

        for (int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                int sum = 0;

                for(int idx = i; idx <= j; idx++){
                    sum += nums[idx];
                }

                if (sum == k){
                    len = Math.max(len, j - i + 1);
                }
            }
        }
        return len;
    }
    public static int better(int[] nums, int k){
        int n = nums.length;
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;

            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum == k){
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        return maxLen;
    }
    public static int optimized(int[] nums, int k){
        int n = nums.length;
        Map<Integer, Integer> m = new HashMap<>();//<prefixsum, index>
        int sum = 0;
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];

            if (sum == k){
                maxLen = i + 1;
            }

            int rem = sum - k;
            if(m.containsKey(rem)){
                int len = i - m.get(rem);
                maxLen = Math.max(maxLen, len);
            }

            if(!m.containsKey(rem)){
                m.put(sum, i);
            }
        }

        return maxLen;

    }
    public static void main(String[] args) {
        int[] nums = {10, 5, 2, 7, 1, 9};
        int k = 15;
        System.out.println("Length of longest subarray having sum k is " + optimized(nums, k));
    }
}
