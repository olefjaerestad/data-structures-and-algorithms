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
            list.get(2).shouldBe("Node 3")

            list.addAtIndex(2, "New Node")
            val actual = list.get(2)
            val expected = "New Node"
            actual.shouldBe(expected)
        }

        test("Should support adding a Node to the start of a list (head)") {
            val list = LinkedList()
            list.head = head()

            list.addAtHead("New Head")
            list.get(0).shouldBe("New Head")
        }

        test("Should support adding a Node to the end of a list") {
            val list = LinkedList()
            list.head = head()
            val newLast = list.addAtTail("New Last")

            newLast.shouldBe("New Last")
        }

        test("Should support deleting a Node from the middle or end of a list") {
            val list = LinkedList()
            list.head = head()
            list.get(2).shouldBe("Node 3")

            list.deleteAtIndex(2)
            list.get(2).shouldBe("Node 4")
        }

        test("Should support deleting a Node from the head of a list") {
            val list = LinkedList()
            list.head = head()
            list.head?.value.shouldBe("Head")
            list.head?.next?.value.shouldBe("Node 2")

            list.deleteAtIndex(0)
            list.head?.value.shouldBe("Node 2")
            list.head?.next?.value.shouldBe("Node 3")
        }
    }
}

fun head(): Node {
    val node5 = Node("Node 5")
    val node4 = Node("Node 4", node5)
    val node3 = Node("Node 3", node4)
    val node2 = Node("Node 2", node3)
    val head = Node("Head", node2)

    return head
}
