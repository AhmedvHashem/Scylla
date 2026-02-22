package old

data class ListNode<T>(var value: T) {
    var next: ListNode<T>? = null
}

fun removeKFromList(node: ListNode<Int>?, k: Int): ListNode<Int>? {
    var head = node
    while (head != null && head.value == k) {
        head = head.next
    }
    var temp = head
    var prev: ListNode<Int>? = null
    while (temp != null) {
        while (temp != null && temp.value != k) {
            prev = temp
            temp = temp.next
        }
        if (temp == null) return head
        prev!!.next = temp.next
        temp = prev.next
    }
    return head
}