class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        numDict = dict()
        for i,val in enumerate(nums):
            if target - val in numDict:
                return [numDict[target - val],i]
            numDict[val] = i
        return []