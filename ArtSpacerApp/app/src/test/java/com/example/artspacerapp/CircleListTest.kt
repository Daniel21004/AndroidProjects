package com.example.artspacerapp

import com.example.artspacerapp.domain.Card
import com.example.artspacerapp.domain.CircularList
import com.example.artspacerapp.domain.Node
import org.junit.Assert
import org.junit.Test

class CircleListTest {

    private val circularList: CircularList = CircularList()
    private val exampleCard: Card = Card(0, "ImageTitle", "Unkown", 2000)

    @Test
    fun addNode() {
        circularList.addNode(exampleCard)
        Assert.assertEquals(1, circularList.size)
    }

    @Test
    fun getNodeByIndex_retrievedFirstNode() {
        circularList.addNode(exampleCard)
        val actualNode = circularList.getByIndex(0)
        val expectedNode = Node(exampleCard)
        Assert.assertEquals(expectedNode.value, actualNode.value)
    }

    @Test
    fun getNodeByIndex_returnErrorByIndexOutOfBounds(){
        circularList.addNode(exampleCard)
        Assert.assertThrows(Exception::class.java){
            circularList.getByIndex(2)
        }
    }

    @Test
    fun getNodeByIndex_returnErrorByNoNodes(){
        Assert.assertThrows(Exception::class.java){
            circularList.getByIndex(2)
        }
    }
}