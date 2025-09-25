package org.example.linked_list

class LinkedList {
    var head: ListNode? = null

    fun get(index: Int): Int {
        var counter = 0
        var node = head

        while (counter <= index) {
            if (counter == index && node is ListNode) {
                return node.value
            } else {
                node = node?.next
                counter++
            }
        }

        return -1
    }

    fun addAtHead(value: Int): Int {
        val newHead = ListNode(value)
        newHead.next = head
        head = newHead
        return newHead.value
    }

    fun addAtTail(value: Int): Int {
        val newTail = ListNode(value)

        if (head == null) {
            head = newTail
            return newTail.value
        }

        var lastNode = head

        while (lastNode is ListNode) {
            if (lastNode.next == null) {
                break
            } else {
                lastNode = lastNode.next
            }
        }

        lastNode?.next = newTail

        return newTail.value
    }

    fun addAtIndex(index: Int, value: Int): Int? {
        val node = ListNode(value)

        if (index == 0) {
            node.next = head
            head = node
            return node.value
        }

        var current = head
        var prev = head
        var counter = 0

        while (counter <= index) {
            if (counter == index) {
                node.next = prev?.next
                prev?.next = node
                return node.value
            } else {
                prev = current
                current = current?.next
                counter++
            }
        }

        return null
    }

    fun deleteAtIndex(index: Int): Int {
        if (head == null) {
            return -1
        }

        if (index == 0) {
            head = head?.next
            return index
        }


        var prev: ListNode? = null
        var current: ListNode? = head
        var counter = 0

        // Start from beginning of list and find the Node pointing to `index`.
        while (current is ListNode) {
            if (counter == index) {
                prev?.next = current.next
                return index
            } else {
                counter++
                prev = current
                current = current.next
            }
        }

        return index
    }

    fun hasCycle(head: ListNode): Boolean {
        var slow: ListNode? = head
        var fast: ListNode? = head

        while (fast is ListNode) {
            slow = slow?.next
            fast = fast.next?.next

            if (fast is ListNode && slow === fast) {
                return true
            }
        }

        return false
    }

    /**
     * @see <a href="https://www.youtube.com/shorts/qgXYgkeEXg8">Explanation</a>
     */
    fun detectCycle(head: ListNode): ListNode? {
        var slow: ListNode? = head
        var fast: ListNode? = head

        // Find meeting point
        while (fast is ListNode) {
            slow = slow?.next
            fast = fast.next?.next

            if (fast is ListNode && slow === fast) {
                break
            }
        }

        if (fast === null) {
            return null
        }

        // Find cycle start
        var p1: ListNode? = head
        while (p1 != slow) {
            p1 = p1?.next
            slow = slow?.next
        }

        return p1
    }
}