package com.joaopbini.blockchain.controller

import com.joaopbini.blockchain.dto.BlockchainTransactionDTO
import com.joaopbini.blockchain.service.BlockchainService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BlockchainController(val service: BlockchainService) {

    @PostMapping("/transaction")
    fun execute(@RequestBody transactionDTO: BlockchainTransactionDTO): BlockchainTransactionDTO {
        return service.process(transactionDTO)
    }

}
