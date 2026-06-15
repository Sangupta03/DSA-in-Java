class strToAtoi {
    public int myAtoi(String s) {
        int res = 0;
        int sign = 1;
        int idx = 0;

        // Skip leading whitespace
        while (idx < s.length() && s.charAt(idx) == ' ') {
            idx++;
        }

        // Handle optional sign
        if (idx < s.length() && (s.charAt(idx) == '-' || s.charAt(idx) == '+')) {
            if (s.charAt(idx) == '-') {
                sign = -1;
            }
            idx++;
        }

        // Convert digits
        while (idx < s.length() && s.charAt(idx) >= '0' && s.charAt(idx) <= '9') {
            // Handle overflow
            if (res > Integer.MAX_VALUE / 10 || 
               (res == Integer.MAX_VALUE / 10 && (s.charAt(idx) - '0') > 7)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            res = res * 10 + (s.charAt(idx) - '0');
						idx++;
        }

        return res * sign;
    }
}