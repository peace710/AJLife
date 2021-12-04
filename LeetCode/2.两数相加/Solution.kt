/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var node:ListNode? = ListNode(0)
        var n1:ListNode? = l1;
        var n2:ListNode? = l2;
        var p:ListNode? = node
        var cf = 0
        while (n1 != null || n2 != null || cf == 1){
            var val1 = if (n1 == null) 0 else n1?.`val`
            var val2 = if (n2 == null) 0 else n2?.`val`
            var sum = val1 + val2 + cf
            node?.`val` = sum % 10
            cf = sum / 10
            n1 = n1?.next
            n2 = n2?.next
            if (n1 != null || n2 != null || cf == 1){
                node?.next = ListNode(0)
                node = node?.next
            }
        }
        return p
    }
}