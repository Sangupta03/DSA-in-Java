package Sliding_window;

import java.util.*;

class subStrWithConcatenationWords {
    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> result = new ArrayList<>();

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        if (s.length() < totalLen)
            return result;

        // Required frequency
        Map<String, Integer> targetCounts = new HashMap<>();

        for (String word : words) {
            targetCounts.put(word, targetCounts.getOrDefault(word, 0) + 1);
        }

        // Try every possible alignment
        //word from start of string could be i=0...i=wordlen to form a valid window
        //dogcat --> dog / ogc /gca to find a valid word in start
        for (int i = 0; i < wordLen; i++) {

            Map<String, Integer> currentCounts = new HashMap<>();

            int left = i;
            int matchedWords = 0;

            for (int right = i; right + wordLen <= s.length(); right += wordLen) {
              //new window is formed for each valid word found in string
                String word = s.substring(right, right + wordLen);

                // Valid word
                if (targetCounts.containsKey(word)) {

                    currentCounts.put(
                        word,
                        currentCounts.getOrDefault(word, 0) + 1
                    );

                    matchedWords++;

                    // Too many copies of this word
                    while (currentCounts.get(word) > targetCounts.get(word)) {

                        String leftWord = s.substring(left, left + wordLen);

                        currentCounts.put(
                            leftWord,
                            currentCounts.get(leftWord) - 1
                        );

                        matchedWords--;
                        left += wordLen;
                    }

                    // Complete window found
                    if (matchedWords == wordCount) {
                        result.add(left);
                    }

                } else {
                    // Invalid word → reset
                    currentCounts.clear();
                    matchedWords = 0;
                    left = right + wordLen;
                }
            }
        }

        return result;
    }
}