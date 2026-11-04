
import java.util.*;

class Solution {
    TreeMap<Integer, Integer> left = new TreeMap<>();
    TreeMap<Integer, Integer> right = new TreeMap<>();
    long sumL = 0, sumR = 0;
    int sizeL = 0, sizeR = 0;

    void add(TreeMap<Integer, Integer> map, int v) {
        map.put(v, map.getOrDefault(v, 0) + 1);
    }

    void remove(TreeMap<Integer, Integer> map, int v) {
        int c = map.get(v);
        if (c == 1) map.remove(v);
        else map.put(v, c - 1);
    }

    void insert(int v) {
        if (sizeL == 0 || v <= left.lastKey()) {
            add(left, v);
            sizeL++;
            sumL += v;
        } else {
            add(right, v);
            sizeR++;
            sumR += v;
        }
        balance();
    }

    void erase(int v) {
        if (left.containsKey(v)) {
            remove(left, v);
            sizeL--;
            sumL -= v;
        } else {
            remove(right, v);
            sizeR--;
            sumR -= v;
        }
        balance();
    }

    void balance() {
        while (sizeL > sizeR + 1) {
            int v = left.lastKey();
            remove(left, v);
            sizeL--;
            sumL -= v;
            add(right, v);
            sizeR++;
            sumR += v;
        }

        while (sizeL < sizeR) {
            int v = right.firstKey();
            remove(right, v);
            sizeR--;
            sumR -= v;
            add(left, v);
            sizeL++;
            sumL += v;
        }
    }

    long getCost() {
        int m = left.lastKey();
        return (long)m * sizeL - sumL + sumR - (long)m * sizeR;
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];

        for (int i = 0; i < x; i++)
            insert(nums[i]);

        cost[0] = getCost();

        for (int i = x; i < n; i++) {
            erase(nums[i - x]);
            insert(nums[i]);
            cost[i - x + 1] = getCost();
        }

        long INF = Long.MAX_VALUE / 4;
        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        Arrays.fill(prev, 0);

        for (int j = 1; j <= k; j++) {
            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {
                curr[i] = curr[i - 1];

                if (i >= x) {
                    curr[i] = Math.min(curr[i],
                        prev[i - x] + cost[i - x]);
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }
}
