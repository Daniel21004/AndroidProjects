package com.example.artspacerapp.domain

data class Node(val value : Card, var previous : Node? = null, var next : Node? = null)
