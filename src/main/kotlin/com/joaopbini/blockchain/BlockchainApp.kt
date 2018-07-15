package com.joaopbini.blockchain

import com.joaopbini.blockchain.service.BlockchainService
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import java.io.IOException
import java.math.BigInteger
import javax.annotation.PostConstruct

@SpringBootApplication
class BlockchainApp(val web3j: Web3j) {

    @PostConstruct
    fun listen() {

        web3j.transactionObservable().subscribe { tx ->

            LOGGER.info("New tx: id={}, block={}, from={}, to={}, value={}", tx.hash, tx.blockHash, tx.from, tx.to, tx.value.toInt())

            try {

                val coinbase = web3j.ethCoinbase().send()
                val transactionCount = web3j.ethGetTransactionCount(tx.from, DefaultBlockParameterName.LATEST).send()
                LOGGER.info("Tx count: {}", transactionCount.transactionCount.toInt())

                if (transactionCount.transactionCount.toInt() % 10 == 0) {

                    val tc = web3j.ethGetTransactionCount(coinbase.address, DefaultBlockParameterName.LATEST).send()
                    val transaction = Transaction.createEtherTransaction(coinbase.address, tc.transactionCount, tx.value, BigInteger.valueOf(21000), tx.from, tx.value)
                    web3j.ethSendTransaction(transaction).send()

                }

            } catch (e: IOException) {
                LOGGER.error("Error getting transactions", e)
            }

        }

        LOGGER.info("Subscribed")

    }

    companion object {

        private val LOGGER = LoggerFactory.getLogger(BlockchainService::class.java)

        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(BlockchainApp::class.java, *args)
        }

    }

}