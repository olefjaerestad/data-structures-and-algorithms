package org.example.linked_list

import kotlin.math.max
import kotlin.math.min

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

    /**
     * @see <a href="https://www.youtube.com/shorts/WjK-_KN0_Ck">Explanation</a>
     * Approach: Find the length `difference` between the lists, then travel `difference`
     * in the longest list, before traveling 1 node at a time afterward.
     */
    fun getIntersectionNodeSlow(headA: ListNode, headB: ListNode): ListNode? {
        var pointerA: ListNode? = headA
        var pointerB: ListNode? = headB
        var lengthA = 0
        var lengthB = 0

        while (pointerA is ListNode) {
            pointerA = pointerA.next
            lengthA++
        }

        while (pointerB is ListNode) {
            pointerB = pointerB.next
            lengthB++
        }

        val lengthDiff = max(lengthA, lengthB) - min(lengthA, lengthB)
        var longPointer: ListNode? = if (lengthA >= lengthB) headA else headB
        var shortPointer: ListNode? = if (lengthA >= lengthB) headB else headA

        for (i in 0..<lengthDiff) {
            longPointer = longPointer?.next
        }

        while (longPointer is ListNode && shortPointer is ListNode) {
            if (longPointer === shortPointer) {
                return longPointer
            }

            longPointer = longPointer.next
            shortPointer = shortPointer.next
        }

        return null
    }

    /**
     * @see <a href="https://www.youtube.com/shorts/WjK-_KN0_Ck">Explanation</a>
     * Approach: Travel to the end of both lists, then move pointers to the start of the other list.
     * This ensures both pointers will always have to travel the exact same number of steps, and
     * they will always meet at the intersection if there is one.
     */
    fun getIntersectionNodeFast(headA: ListNode, headB: ListNode): ListNode? {
        var pointerA: ListNode? = headA
        var pointerB: ListNode? = headB
        var movedPointerAToB = false
        var movedPointerBToA = false

        while (pointerA is ListNode && pointerB is ListNode) {
            if (pointerA == pointerB) {
                return pointerA
            }


            // Approach 1: Using a `when` expression:
//            pointerA = when {
//                pointerA.next is ListNode -> pointerA.next
//                !movedPointerAToB -> {
//                    movedPointerAToB = true
//                    headB
//                }
//                else -> null
//            }
//            pointerB = when {
//                pointerB.next is ListNode -> pointerB.next
//                !movedPointerBToA -> {
//                    movedPointerBToA = true
//                    headA
//                }
//                else -> null
//            }

            // Approach 2: Using `if` statements:
            val pointerANext = pointerA.next
            val pointerBNext = pointerB.next
            pointerA = pointerANext ?: if (movedPointerAToB) null else headB
            pointerB = pointerBNext ?: if (movedPointerBToA) null else headA

            if (!movedPointerAToB && pointerANext == null) {
                movedPointerAToB = true
            }
            if (!movedPointerBToA && pointerBNext == null) {
                movedPointerBToA = true
            }
        }

        return null
    }

    /**
     * @see <a href="https://www.youtube.com/shorts/bkYk0yWi6zo">Explanation</a>
     * Approach: Give fast pointer a head start of n steps. When fast has reached end of list,
     * slow is at the node _before_ the one we want to remove.
     * Special case: we insert a dummy Node ahead of `head`, in case it's `head` we want to remove.
     */
    fun removeNthFromEnd(head: ListNode, n: Int): ListNode? {
        val dummy = ListNode(-1, head)
        var slow: ListNode? = dummy
        var fast: ListNode? = dummy

        // Give fast a head start of n steps
        for (i in 0..<n) {
            fast = fast?.next
        }

        while (fast?.next is ListNode) {
            slow = slow?.next
            fast = fast.next
        }

        slow?.next = slow.next?.next
        // Will either be head, the Node after head or null:
        return dummy.next
    }

    /**
     * Approach: Looping through the list, keep pointers to current node and start of list.
     * For each iteration, move `current` to start of list and set `start` to `current`.
     */
    fun reverseListCustomImplementation(head: ListNode): ListNode {
        var start = head
        var current: ListNode? = head.next

        while (current is ListNode) {
            val currentNext = current.next
            current.next = start
            start = current
            current = currentNext
        }

        head.next = null

        return start
    }

    /**
     * @see <a href="https://www.youtube.com/shorts/uyTL80yvTrw">Explanation</a>
     * Approach: Looping through the list, keep pointers to previous, current and next nodes.
     * For each iteration, update `current.next` to prev.
     */
    fun reverseList(head: ListNode): ListNode? {
        var prev: ListNode? = null
        var curr: ListNode? = head
        var next: ListNode? = head.next

        while (curr is ListNode) {
            curr.next = prev
            prev = curr
            curr = next
            next = next?.next
        }

        return prev
    }
}