class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        max = 0
        current = 0
        l = len(s)
        index = 0
        for i in range(0,l):
            current = 1
            for j in range(index,i):
                if (s[i] == s[j]):
                    index = j + 1
                    break
                current +=1
            if max < current:
                max = current
        return max