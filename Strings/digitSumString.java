class digitSumString {
    public String digitSum(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        
        while (sb.length() > k) {
            StringBuilder nextSb = new StringBuilder();
            int currentSum = 0;
            
            for (int i = 0; i < sb.length(); i++) {
                currentSum += sb.charAt(i) - '0';
                
                // When we finish a group of size k or reach the end of the string
                if ((i + 1) % k == 0 || i == sb.length() - 1) {
                    nextSb.append(currentSum);
                    currentSum = 0;
                }
            }
            sb = nextSb;
        }
        
        return sb.toString();
    }
}