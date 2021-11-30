/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* twoSum(int* nums, int numsSize, int target, int* returnSize){
    int* result = NULL;
    for (int i = 0 ; i < numsSize - 1;i++){
        for (int j = i + 1 ; j < numsSize;j++){
            if (nums[i] + nums[j] == target){
                result = (int*)malloc(sizeof(int)*2);
                result[0] = i;
                result[1] = j;
                *returnSize = 2;
                return result;
            }
        }
    }
    return result;
}