package com.joaopbini.blockchain.service

import com.joaopbini.blockchain.dto.BlockchainTransactionDTO
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import java.math.BigInteger

@Service
class BlockchainService(val web3j: Web3j) {

    fun process(trx: BlockchainTransactionDTO): BlockchainTransactionDTO {

        val accounts = web3j.ethAccounts().send()
        val transactionCount = web3j.ethGetTransactionCount(accounts.accounts[trx.fromId], DefaultBlockParameterName.LATEST).send()

        val transaction = Transaction.createEtherTransaction(
                accounts.accounts[trx.fromId], transactionCount.transactionCount, BigInteger.valueOf(trx.value),
                BigInteger.valueOf(21000), accounts.accounts[trx.toId], BigInteger.valueOf(trx.value))

        val response = web3j.ethSendTransaction(transaction).send()

        if (response.error != null) {
            trx.isAccepted = false
            LOGGER.info("Tx rejected: {}", response.error.message)
            return trx
        }

        trx.isAccepted = true
        val txHash = response.transactionHash
        LOGGER.info("Tx hash: {}", txHash)

        trx.id = txHash
        val receipt = web3j.ethGetTransactionReceipt(txHash).send()

        receipt.transactionReceipt.ifPresent { transactionReceipt -> LOGGER.info("Tx receipt:  {}", transactionReceipt.cumulativeGasUsed.toInt()) }

        return trx

    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(BlockchainService::class.java)
    }

}