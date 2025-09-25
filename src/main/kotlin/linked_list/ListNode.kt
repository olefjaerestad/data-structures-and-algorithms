package org.example.linked_list

data class ListNode(val value: Int, var next: ListNode? = null) {
    override fun toString(): String = "ListNode(value = ${this.value}, next = ${if (this.next is ListNode) "ListNode(value = ${this.next?.value})" else "null"})"
}