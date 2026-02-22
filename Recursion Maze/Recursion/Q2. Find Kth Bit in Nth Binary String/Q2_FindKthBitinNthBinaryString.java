class Solution {
    public char findKthBit(int n, int k) {
        
        // Base case:
        // When n = 1, the string S1 = "0"
        // So the only possible answer is '0'
        if (n == 1) return '0';
        
        // Length of Sn is (2^n - 1)
        // (1 << n) means 2^n
        int length = (1 << n) - 1;
        
        // Middle position of Sn
        // Since length is always odd, middle is:
        // (length / 2) + 1
        int mid = length / 2 + 1;
        
        // Important rule:
        // In Sn, the middle character is always '1'
        if (k == mid) return '1';
        
        // If k is in the first half of Sn
        // First half is exactly same as Sn-1
        // So we recursively search in Sn-1 at same position k
        if (k < mid) return findKthBit(n - 1, k);
        
        // If k is in the second half:
        // Second half = reverse(invert(Sn-1))
        // So:
        // 1) Convert k to the mirrored index in Sn-1
        //    mirrored index = length - k + 1
        // 2) Recursively find that bit in Sn-1
        // 3) Invert the result (0 becomes 1, 1 becomes 0)
        
        char result = findKthBit(n - 1, length - k + 1);
        
        // Invert the bit because second half is inverted
        if (result == '0') return '1';
        else return '0';
    }
}