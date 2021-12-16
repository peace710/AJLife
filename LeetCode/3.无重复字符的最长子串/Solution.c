int lengthOfLongestSubstring(char * s){
    int max = 0;
    int current = 0; 
    int len = strlen(s);
    int index = 0;
    for (int i = 0;i<len;i++){
        current = 1;
        for (int j = index ; j < i ;j++){
            if (s[i] == s[j]){
                index = j+1;
                break;
            }
            current++;
        } 
        if (max < current){
            max = current;
        }
    }
    return max;    
}