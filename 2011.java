
class Solution {
    public int finalValueAfterOperations(String[] operations) {
        int X = 0;

        for (int i = 0; i < operations.length; i++) {
            if (operations[i].contains("+")) {
                X++;
            } else {
                X--;
            }
        }

        return X;
    }
}
