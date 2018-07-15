# kotlin-spring-blockchain-eth

#### 1. Running Ethereum locally

Here’s the command that starts Docker container in development mode and exposes Ethereum RPC API on port 8545.

````
$ docker run -d --name ethereum -p 8545:8545 -p 30303:30303 ethereum/client-go --rpc --rpcaddr "0.0.0.0" --rpcapi="db,eth,net,web3,personal" --rpccorsdomain "*" --dev
````

To achieve it we need to run Geth’s interactive JavaScript console inside Docker container.

````
$ docker exec -it ethereum geth attach ipc:/tmp/geth.ipc
````

#### 2. Managing Ethereum node using JavaScript console

After running JavaScript console you can easily display default account (coinbase), the list of all available accounts and their balances. 

Some common commands:

````
- To get some informations
eth.coinbase
eth.accounts
eth.getBalance(eth.accounts[0])

- To create some test accounts 
personal.newAccount("password")

- To do a transaction
eth.sendTransaction({from: eth.coinbase, to: eth.accounts[1], value: 1000})
````

#### 3. To do a transaction by SpringBoot Web Service

- Run BlockchainApp

- **Access:** http:localhost:8090/swagger-ui.html
