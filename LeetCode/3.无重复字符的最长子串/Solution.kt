class Solution {
    fun lengthOfLongestSubstring(s: String): Int {
        var len = s.length
        var current = 0
        var max = 0
        var index = 0
        for (i in 0..len - 1){
            current = 1
            for (j in index..i - 1){
                if (s[i] == s[j]){
                     index = j + 1
                     break
                }
                current++
            }
            if (max < current){
                max = current
            }
        }
        return max
    }
}