package org.example.binary_tree.tests.linked_list

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.example.linked_list.LinkedList
import org.example.linked_list.Node

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
    }
}

fun head(): Node {
    val node5 = Node(4)
    val node4 = Node(3, node5)
    val node3 = Node(2, node4)
    val node2 = Node(1, node3)
    val head = Node(0, node2)

    return head
}

fun headWithCycle(): Node {
    val node5 = Node(4)
    val node4 = Node(3, node5)
    val node3 = Node(2, node4)
    val node2 = Node(1, node3)
    val head = Node(0, node2)
    node5.next = node3

    return head
}
