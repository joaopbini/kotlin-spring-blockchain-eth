package com.joaopbini.blockchain.dto

data class BlockchainTransactionDTO(

        var id: String? = null,
        val fromId: Int = 0,
        val toId: Int = 0,
        val value: Long = 0,
        var isAccepted: Boolean = false

)
