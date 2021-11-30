class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = mutableMapOf<Int,Int>()
        val result = IntArray(2)
        for (i in nums.indices){
            var j:Int? = map.get(target - nums[i])
            if (j != null){
                result.set(0,j)
                result.set(1,i)
                return result
            }
            map.put(nums[i],i)
        }
        return result
    }
}