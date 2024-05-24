# File Retrieval System Sockets

This project is a distributed system designed for efficient indexing and searching of large datasets of text files. It utilizes multiple clients to distribute the indexing workload, with a central server managing client requests and processing search operations.


## Project Overview

The system aims to handle the indexing and searching of text files by distributing these tasks across multiple clients. A central server coordinates the clients, managing communication and processing search queries.

## File Structure

The project directory is organized as follows:

```
.
├── client
│   ├── Client.java
│   ├── ClientAutomation.java
│   ├── ClientMain.java
├── common
│   ├── IndexStore.java
│   ├── IndexMessage.java
│   ├── IndexEntry.java
│   ├── SearchMessage.java
│   ├── SearchResult.java
├── server
│   ├── ClientHandler.java
│   ├── Dispatcher.java
│   ├── Server.java
│   ├── ServerMain.java
├── datasets
│   ├── Dataset1
│   ├── Dataset2
│   ├── Dataset3
│   ├── Dataset4
│   ├── Dataset5
├── README.md
```

### Client Files
- `Client.java`: Handles the client's main functionality.
- `ClientAutomation.java`: Manages automated client tasks.
- `ClientMain.java`: Entry point for the client application.

### Common Files
- `IndexStore.java`: Data structure for storing indexed terms.
- `IndexMessage.java`: Defines the format for index messages.
- `IndexEntry.java`: Represents an entry in the index.
- `SearchMessage.java`: Defines the format for search messages.
- `SearchResult.java`: Represents a search result.

### Server Files
- `ClientHandler.java`: Manages individual client connections.
- `Dispatcher.java`: Dispatches tasks to client handlers.
- `Server.java`: Core server logic.
- `ServerMain.java`: Entry point for the server application.

### Dataset Files
- `Dataset1` to `Dataset5`: Directories containing text files for indexing and searching.

## Building the Project

To build the project, follow these steps:

1. **Clone the Repository**: Clone the project repository to your local machine.
   ```bash
   git clone <repository-url>
   cd <repository-directory>
   ```

2. **Compile the Source Files**: Use the `javac` command to compile the source files.
   ```bash
   javac -d build client/*.java common/*.java server/*.java
   ```

This command compiles all Java source files and places the compiled classes in the `build` directory.

## Executing the Program

### Step 1: Start the Server

Start the server before launching any clients. Use the following command to start the server:
```bash
java -cp build server.ServerMain <port>
```
Replace `<port>` with the desired port number, for example:
```bash
java -cp build server.ServerMain 8080
```

### Step 2: Start the Client(s)

Once the server is running, you can start the client(s). Use the following command to start a client:
```bash
java -cp build client.ClientMain <serverIp> <port>
```
Replace `<serverIp>` with the server's IP address and `<port>` with the server's port number, for example:
```bash
java -cp build client.ClientMain 127.0.0.1 8080
```

### Step 3: Index a Dataset

To index a dataset, invoke the `index` method on the client:
```java
Client client = new Client();
client.connect("127.0.0.1", 8080);
client.index("<datasetPath>");
```
Replace `<datasetPath>` with the path to the dataset folder, for example:
```java
client.index("datasets/Dataset1");
```

### Step 4: Perform a Search

To perform a search, use the `search` method on the client:
```java
client.search("<query>");
```
Replace `<query>` with your search query, for example:
```java
client.search("example AND search");
```

### Step 5: Disconnect the Client

To disconnect the client from the server, use the `quit` method:
```java
client.quit();
```

## Performance Metrics

To evaluate the performance of the indexing operation, follow these steps:

1. **Run with Different Client Configurations**: Execute the program with different numbers of clients (1, 2, 4, and 8 clients) and measure the wall time for indexing each dataset.
2. **Calculate Throughput**: Determine the throughput in MB/s by dividing the total dataset size by the total indexing time.
3. **Record the Results**: Present the results in a tabular format as shown below.

| Dataset  | 1 Client (MB/s) | 2 Clients (MB/s) | 4 Clients (MB/s) | 8 Clients (MB/s) |
|----------|------------------|------------------|------------------|------------------|
| Dataset1 |        x.x       |       x.x        |      x.x         |      x.x         |
| Dataset2 |        x.x       |       x.x        |      x.x         |      x.x         |
| Dataset3 |        x.x       |       x.x        |      x.x         |      x.x         |
| Dataset4 |        x.x       |       x.x        |      x.x         |      x.x         |
| Dataset5 |        x.x       |       x.x        |      x.x         |      x.x         |

This evaluation helps in understanding the scalability and efficiency of the system with varying client configurations.
