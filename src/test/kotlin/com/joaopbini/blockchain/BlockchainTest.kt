package com.joaopbini.blockchain

import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.web3j.protocol.Web3j

@SpringBootTest
@RunWith(SpringRunner::class)
class BlockchainTest {

    @Autowired
    internal var web3j: Web3j? = null

    @Test
    fun test() {
        //        LOGGER.info("Generating wallet file...");
        //        String file = WalletUtils.generateFullNewWalletFile("piot123", null);
        //        Credentials c = WalletUtils.loadCredentials("piot123", file);
        //        LOGGER.info("Generating wallet file: {}", file);
        //        EthAccounts accounts = web3j.ethAccounts().send();
        //
        //        LOGGER.info("Accounts size: ", accounts.getAccounts().size());
        //        accounts.getAccounts().forEach(acc -> LOGGER.info("Account", acc));
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(BlockchainTest::class.java)
    }

}
