package StacksANDqueues;

public class minSwapsStringBalanced {
    public int minSwaps(String s) {
        int imbalance = 0;
        int maxImbalance = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '[') {
                imbalance--; // An opening bracket reduces imbalance
            } else {
                imbalance++; // A closing bracket increases imbalance
            }
            maxImbalance = Math.max(maxImbalance, imbalance);
        }

        return (maxImbalance + 1) / 2;
    }
} 
