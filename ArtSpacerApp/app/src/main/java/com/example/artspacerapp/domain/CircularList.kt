package com.example.artspacerapp.domain

class CircularList(private var firstNode: Node? = null, private var lastNode: Node? = null, var size : Int = 0) {

    fun addNode(card: Card) {
        val cardNode = Node(card)
        if (firstNode == null) {
            addFirstNode(cardNode)
        }else {
            cardNode.previous = lastNode
            lastNode!!.next = cardNode
            cardNode.next = firstNode
            lastNode = cardNode
            firstNode!!.previous = lastNode
        }
        size++
    }

    private fun addFirstNode(cardNode: Node) {
        firstNode = cardNode
        lastNode = cardNode
        cardNode.next = cardNode
        cardNode.previous = cardNode
    }

    fun getByIndex(index : Int) : Node{
        if (index >= size || firstNode == null){
            throw Exception("Index out of bounds or no nodes")
        }
        var aux = firstNode
        var i = 0
        while (i < index){
            aux = aux?.next
            i++
        }
        return aux!!
    }

    fun printList(){
        var aux = firstNode
        var flag = true
        while (flag){
            println("current: ${aux?.value?.title}, next: ${aux?.next?.value?.title}, previous: ${aux?.previous?.value?.title}")
            aux = aux?.next
            flag = aux != firstNode
        }
    }
}

fun main(){
    val circulaList = CircularList()
    circulaList.addNode(Card(1, "hola", "author", 2020))
    circulaList.addNode(Card(1, "hola2", "author", 2020))
    circulaList.addNode(Card(1, "hola3", "author", 2020))
    circulaList.printList()
}