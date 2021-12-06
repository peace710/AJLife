# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        node = ListNode()
        p = node
        cf = 0
        while l1 is not None or l2 is not None or cf == 1:
            if (l1 is not None):
                val1 = l1.val
                l1 = l1.next
            else:
                val1 = 0
            if (l2 is not None):
                val2 = l2.val
                l2 = l2.next
            else:
                val2 = 0
            sum = val1 + val2 + cf
            node.val = sum % 10
            cf = sum // 10
            if l1 is not None or l2 is not None or cf == 1:
                node.next = ListNode()
                node = node.next
        return p