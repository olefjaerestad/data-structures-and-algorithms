package org.example.linked_list

class LinkedList {
    var head: Node? = null

    fun get(index: Int): Int {
        var counter = 0
        var node = head

        while (counter <= index) {
            if (counter == index && node is Node) {
                return node.value
            } else {
                node = node?.next
                counter++
            }
        }

        return -1
    }

    fun addAtHead(value: Int): Int {
        val newHead = Node(value)
        newHead.next = head
        head = newHead
        return newHead.value
    }

    fun addAtTail(value: Int): Int {
        val newTail = Node(value)

        if (head == null) {
            head = newTail
            return newTail.value
        }

        var lastNode = head

        while (lastNode is Node) {
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
        val node = Node(value)

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


        var prev: Node? = null
        var current: Node? = head
        var counter = 0

        // Start from beginning of list and find the Node pointing to `index`.
        while (current is Node) {
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

    fun hasCycle(head: Node): Boolean {
        var slow: Node? = head
        var fast: Node? = head

        while (fast is Node) {
            slow = slow?.next
            fast = fast.next?.next

            if (fast is Node && slow === fast) {
                return true
            }
        }

        return false
    }
}