/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */


struct ListNode* addTwoNumbers(struct ListNode* l1, struct ListNode* l2){
    struct ListNode *node = (struct ListNode*)malloc(sizeof(struct ListNode));
    node->next = NULL;
    node->val = 0;
    struct ListNode *p = node;
    int cf = 0;
    while (l1 != NULL || l2 != NULL || cf == 1){
        int val1 = l1 == NULL? 0:l1->val;
        int val2 = l2 == NULL? 0:l2->val;
        int sum = val1 + val2 + cf;
        node->val = sum % 10;
        cf = sum / 10;
        if (l1){
            l1 = l1->next;
        }
        if (l2){
            l2 = l2->next;
        }
        if (l1 != NULL || l2 != NULL || cf == 1){
            node->next = (struct ListNode*)malloc(sizeof(struct ListNode));
            node = node->next;
            node->val = 0;
            node->next = NULL;
        }
    }
    return p;
}