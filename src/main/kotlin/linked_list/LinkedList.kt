package org.example.linked_list

class LinkedList {
    var head: Node? = null

    fun get(index: Int): String? {
        var counter = 0
        var node = head

        while (counter <= index) {
            if (counter == index) {
                return node?.value
            } else {
                node = node?.next
                counter++
            }
        }

        return null
    }

    fun addAtHead(value: String): String {
        val newHead = Node(value)
        newHead.next = head
        head = newHead
        return newHead.value
    }

    fun addAtTail(value: String): String {
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

    fun addAtIndex(index: Int, value: String): String? {
        val node = Node(value)

        if (head == null) {
            head = node
            return node.value
        }

        var currentNode = head
        var counter = 0

        while (counter < index) {
            if (counter == index - 1) {
                node.next = currentNode?.next
                currentNode?.next = node
                return node.value
            } else {
                currentNode = currentNode?.next
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
}