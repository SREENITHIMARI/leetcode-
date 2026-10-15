
import java.util.HashSet;

class Solution {
    public boolean isHappy(int n) {
        HashSet<Integer> seen_num = new HashSet<>();

        while (n != 1 && !seen_num.contains(n)) {
            seen_num.add(n);
            int ts = 0;

            while (n != 0) {
                int digit = n % 10;
                ts += digit * digit;
                n /= 10;
            }

            n = ts;
        }

        return n == 1;
    }
}
