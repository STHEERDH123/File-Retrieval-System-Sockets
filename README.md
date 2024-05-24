# File-Retrieval-System-Sockets

This project is a distributed system for efficiently indexing and searching large datasets of text files. It leverages multiple clients to distribute the workload of indexing and performs search operations through a central server.

## Project Overview

The system is designed to efficiently handle indexing and searching tasks by distributing the workload across multiple clients. The server manages the clients, processes their requests, and coordinates the overall operation.

## Project Structure

Here is the structure of the project directory:

```
.
├── client
|   ├── AppInterface.java
│   ├── ClientConnection.java
|   ├── ClientApp.java
│   ├── ClientProcessingEngine.java
├── common
│   ├── IndexStore.java
│   ├── MessageProtocol.java
│   ├── SearchResult.java
├── server
|   ├── AppInterface.java
|   ├── ServerProcessingEngine.java
│   ├── ServerApp.java
│   ├── WorkerThread.java
├── datasets
│   ├── Dataset1
│   ├── Dataset2
│   ├── Dataset3
│   ├── Dataset4
│   ├── Dataset5
├── README.md

```

- **client/**: Client-side implementation files.
  - `ClientConnection.java`: Manages the communication with the server.
  - `ClientProcessingEngine.java`: Manages indexing and searching operations on the client.

- **common/**: Shared classes used by both client and server.
  - `IndexStore.java`: Data structure for storing the index.
  - `MessageProtocol.java`: Defines the communication protocol.
  - `SearchResult.java`: Represents a search result.

- **server/**: Server-side implementation files.
  - `Server.java`: Main server class to handle client connections.
  - `WorkerThread.java`: Manages individual client connections.

- **datasets/**: Sample datasets for testing the indexing and searching functionality.

- **README.md**: Documentation file for the project.

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
java -cp build server.Server <port>
```
Replace `<port>` with the desired port number, for example:
```bash
java -cp build server.Server 8080
```

### Step 2: Start the Client(s)

Once the server is running, you can start the client(s). Use the following command to start a client:
```bash
java -cp build client.ClientProcessingEngine <serverIp> <port>
```
Replace `<serverIp>` with the server's IP address and `<port>` with the server's port number, for example:
```bash
java -cp build client.ClientProcessingEngine 127.0.0.1 8080
```

### Step 3: Index a Dataset

To index a dataset, invoke the `index` method on the client:
```java
ClientProcessingEngine engine = new ClientProcessingEngine();
engine.connect("127.0.0.1", 8080);
engine.index("<datasetPath>");
```
Replace `<datasetPath>` with the path to the dataset folder, for example:
```java
engine.index("datasets/Dataset1");
```

### Step 4: Perform a Search

To perform a search, use the `search` method on the client:
```java
engine.search("<query>");
```
Replace `<query>` with your search query, for example:
```java
engine.search("example AND search");
```

### Step 5: Disconnect the Client

To disconnect the client from the server, use the `quit` method:
```java
engine.quit();
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
