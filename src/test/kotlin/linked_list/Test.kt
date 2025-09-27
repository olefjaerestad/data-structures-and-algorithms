package org.example.binary_tree.tests.linked_list

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.example.linked_list.LinkedList
import org.example.linked_list.ListNode

class Test: FunSpec() {
    init {
        test("Should support adding a Node in the middle of a list") {
            val list = LinkedList()
            list.head = head()
            list.get(2).shouldBe(2)

            list.addAtIndex(2, 123)
            val actual = list.get(2)
            val expected = 123
            actual.shouldBe(expected)
        }

        test("Should support adding a Node to the start of a list (head)") {
            val list = LinkedList()
            list.head = head()

            list.addAtHead(123)
            list.get(0).shouldBe(123)
        }

        test("Should support adding a Node to the end of a list") {
            val list = LinkedList()
            list.head = head()
            val newLast = list.addAtTail(123)

            newLast.shouldBe(123)
        }

        test("Should support deleting a Node from the middle or end of a list") {
            val list = LinkedList()
            list.head = head()
            list.get(2).shouldBe(2)

            list.deleteAtIndex(2)
            list.get(2).shouldBe(3)
        }

        test("Should support deleting a Node from the head of a list") {
            val list = LinkedList()
            list.head = head()
            list.head?.value.shouldBe(0)
            list.head?.next?.value.shouldBe(1)

            list.deleteAtIndex(0)
            list.head?.value.shouldBe(1)
            list.head?.next?.value.shouldBe(2)
        }

        context("Given a head, should detect if the list contains a cycle or not") {
            test("Does contain a cycle") {
                val list = LinkedList()
                val actual = list.hasCycle(headWithCycle())
                val expected = true
                actual.shouldBe(expected)
            }

            test("Does not contain a cycle") {
                val list = LinkedList()
                val actual = list.hasCycle(head())
                val expected = false
                actual.shouldBe(expected)
            }
        }

        context("Given a head, should detect the Node where a cycle begins") {
            test("Does contain a cycle, where cycle starts in the middle of the list") {
                val list = LinkedList()
                val head = headWithCycle()
                val actual = list.detectCycle(head)
                val expected = head.next?.next
                actual?.shouldBe(expected)
            }

            test("Does contain a cycle, where cycle starts at head") {
                val list = LinkedList()
                val head = headWithCycleAtHead()
                val actual = list.detectCycle(head)
                val expected = head
                actual?.shouldBe(expected)
            }

            test("Does not contain a cycle") {
                val list = LinkedList()
                val actual = list.detectCycle(head())
                actual.shouldBeNull()
            }
        }

        context("Given the heads of 2 LinkedLists, should find the Node where the lists intersect") {
            test("Does contain an intersection") {
                val list = LinkedList()
                val heads = headsWithIntersection()
                val actual = list.getIntersectionNodeFast(heads.first, heads.second)
                val expected = heads.first.next

                actual.shouldNotBeNull()
                actual.shouldBe(expected)
            }

            test("Does not contain an intersection") {
                val list = LinkedList()
                val actual = list.getIntersectionNodeSlow(head(), head())
                actual.shouldBeNull()
            }
        }
    }
}

fun head(): ListNode {
    val node5 = ListNode(4)
    val node4 = ListNode(3, node5)
    val node3 = ListNode(2, node4)
    val node2 = ListNode(1, node3)
    val head = ListNode(0, node2)

    return head
}

fun headWithCycle(): ListNode {
    val node5 = ListNode(4)
    val node4 = ListNode(3, node5)
    val node3 = ListNode(2, node4)
    val node2 = ListNode(1, node3)
    val head = ListNode(0, node2)
    node5.next = node3

    return head
}

fun headWithCycleAtHead(): ListNode {
    val node2 = ListNode(1)
    val head = ListNode(0, node2)
    node2.next = head

    return head
}

fun headsWithIntersection(): Pair<ListNode, ListNode> {
    val node5 = ListNode(4)
    val node4 = ListNode(3, node5)
    val node3 = ListNode(2, node4)
    val node2 = ListNode(1, node3)

    val head1 = ListNode(0)
    val head2 = node2

    head1.next = node2

    return Pair<ListNode, ListNode>(head1, head2)
}