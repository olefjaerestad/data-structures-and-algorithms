package org.example.binary_tree.tests.binary_tree

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.example.binary_tree.Node

class Test: FunSpec() {
    init {
        test("Depth First Search returns Nodes from left to right") {
            val actual = createDepthFirstSearch()(node1)
            val expected = listOf<String>(
                node1.value,
                node2.value,
                node4.value,
                node8.value,
                node9.value,
                node5.value,
                node10.value,
                node11.value,
                node3.value,
                node6.value,
                node12.value,
                node13.value,
                node7.value,
            )

            actual.shouldBe(expected)
        }

        test("Breadth First Search returns shallow Nodes first and deep Nodes last") {
            val actual = breadthFirstSearch(node1)
            val expected = listOf<String>(
                node1.value,
                node2.value,
                node3.value,
                node4.value,
                node5.value,
                node6.value,
                node7.value,
                node8.value,
                node9.value,
                node10.value,
                node11.value,
                node12.value,
                node13.value,
            )

            actual.shouldBe(expected)
        }
    }
}

fun createDepthFirstSearch(): (node: Node) -> List<String> {
    val nodes = mutableListOf<Node>()
    val values = mutableListOf<String>()

    fun depthFirstSearch(node: Node): MutableList<String> {
        nodes.add(node)
        values.add(node.value)

        while (nodes.isNotEmpty()) {
            val n = nodes.removeFirst()

            if (n.left is Node) {
                depthFirstSearch(n.left)
            }
            if (n.right is Node) {
                depthFirstSearch(n.right)
            }
        }

        return values
    }

    return {node: Node ->
        depthFirstSearch(node)
    }
}

fun breadthFirstSearch(node: Node): List<String> {
    val nodes = mutableListOf<Node>(node)
    val values = mutableListOf<String>(node.value)

    while (nodes.isNotEmpty()) {
        val node = nodes.removeFirst()

        if (node.left is Node) {
            nodes.add(node.left)
            values.add(node.left.value)
        }
        if (node.right is Node) {
            nodes.add(node.right)
            values.add(node.right.value)
        }
    }

    return values
}

val node13 = Node("Node 13")
val node12 = Node("Node 12")
val node11 = Node("Node 11")
val node10 = Node("Node 10")
val node9 = Node("Node 9")
val node8 = Node("Node 8")
val node7 = Node("Node 7")
val node6 = Node("Node 6", node12, node13)
val node5 = Node("Node 5", node10, node11)
val node4 = Node("Node 4", node8, node9)
val node3 = Node("Node 3", node6, node7)
val node2 = Node("Node 2", node4, node5)
val node1 = Node("Node 1", node2, node3)
