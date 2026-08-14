package Sliding_window;

import java.util.*;

class maxLenSubstringWithAtmost2 {
    public int maximumLengthSubstring(String s) {
        HashMap<Character, Integer> hp = new HashMap<>();
        int n = s.length();
        int left = 0;
        int maxLen = 0;
        for (int right = 0; right < n; right++) {
            char ch = s.charAt(right);
            hp.put(ch, hp.getOrDefault(ch, 0) + 1);

            while (hp.get(ch) > 2) {
                char l = s.charAt(left);
                if (hp.get(l) == 0) hp.remove(l);
                hp.put(l, hp.get(l) - 1);
                left++;
            }
            maxLen = Math.max(right - left + 1, maxLen);
        }
        return maxLen;
    }
}