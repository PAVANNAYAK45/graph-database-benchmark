\# 🚀 Graph Database Benchmark API



A Spring Boot application that benchmarks the performance of \*\*CognoDB\*\*, a managed graph database compatible with the Neo4j Bolt Driver. The application automatically loads a real-world graph dataset, imports it into the database, and measures the performance of common graph operations such as traversal, lookup, aggregation, and mixed workloads.



\---



\## 📖 Overview



Graph databases are designed to efficiently store and query highly connected data. This project evaluates the performance of graph database operations using a real-world dataset and provides benchmark results that help analyse database efficiency under different workloads.



The application performs the following tasks automatically:



\- Connects to a CognoDB instance

\- Downloads and extracts the Wiki-Vote dataset

\- Parses graph data into nodes and relationships

\- Loads data into CognoDB

\- Executes multiple benchmark workloads

\- Displays execution time for each iteration



\---



\## ✨ Features



\- 🔗 Connects securely to CognoDB using the Neo4j Java Driver

\- 📂 Downloads and extracts the Wiki-Vote graph dataset

\- 📊 Loads over \*\*7,000 nodes\*\* and \*\*100,000+ relationships\*\*

\- ⚡ Traversal Benchmark

\- 🔍 Lookup Benchmark

\- 📈 Aggregation Benchmark

\- 🔄 Mixed Workload Benchmark

\- 📋 Automatic verification of imported graph data

\- ☕ Built with Spring Boot and Maven



\---



\## 🛠️ Tech Stack



| Technology | Purpose |

|------------|---------|

| Java 21 | Programming Language |

| Spring Boot 3 | Backend Framework |

| Maven | Build Tool |

| CognoDB | Graph Database |

| Neo4j Java Driver | Database Connectivity |

| Cypher | Graph Query Language |

| Git | Version Control |

| GitHub | Source Code Hosting |



\---



\## 🏗️ Project Architecture



```

benchmark-api

│

├── benchmark

│   ├── Traversal Benchmark

│   ├── Lookup Benchmark

│   ├── Aggregation Benchmark

│   └── Mixed Workload Benchmark

│

├── config

│   └── Neo4j Configuration

│

├── dataset

│   ├── Dataset Downloader

│   ├── Dataset Extractor

│   └── Dataset Parser

│

├── loader

│   └── Graph Data Loader

│

├── manager

│   └── Benchmark Manager

│

├── metrics

│   └── Metrics Calculator

│

├── service

│   └── CognoDB Service

│

└── BenchmarkApiApplication

```



\---



\## 📂 Dataset



This project uses the \*\*Wiki-Vote\*\* network dataset.



Dataset Statistics



\- Nodes: \*\*7,115\*\*

\- Relationships: \*\*103,689\*\*



The dataset represents voting relationships between Wikipedia administrators.



\---



\## ⚙️ Benchmark Types



\### 1. Traversal Benchmark



Measures the performance of graph traversal queries by navigating connected nodes.



\---



\### 2. Lookup Benchmark



Measures node retrieval performance using indexed properties.



\---



\### 3. Aggregation Benchmark



Evaluates aggregate operations such as counting and grouping.



\---



\### 4. Mixed Workload Benchmark



Simulates a realistic workload by combining multiple graph operations.



\---



\## 📈 Sample Output



```

Traversal Benchmark



Iteration 1 : 2098 ms

Iteration 2 : 1720 ms

Iteration 3 : 1439 ms



...



Lookup Benchmark



Iteration 1 : 241 ms

Iteration 2 : 238 ms



...



Aggregation Benchmark



Iteration 1 : 511 ms

Iteration 2 : 772 ms



...



Mixed Workload Benchmark



Iteration 1 : 1239 ms

Iteration 2 : 1030 ms

```



\---



\## 🔧 Prerequisites



Before running the project, install:



\- Java 21

\- Maven

\- Git

\- CognoDB Cloud Account



\---



\## ⚙️ CognoDB Configuration



Update your `application.properties` file:



```properties

cognodb.uri=bolt+s://your-instance.databases.cognodb.com

cognodb.username=cognodb

cognodb.password=your-password

```



\---



\## ▶️ Running the Project



Clone the repository



```bash

git clone https://github.com/PAVANNAYAK45/graph-database-benchmark.git

```



Navigate into the project



```bash

cd graph-database-benchmark

```



Build the project



```bash

mvn clean install

```



Run the application



```bash

mvn spring-boot:run

```



\---



\## 📊 Benchmark Workflow



```

Start Application

&#x20;       │

&#x20;       ▼

Connect to CognoDB

&#x20;       │

&#x20;       ▼

Download Dataset

&#x20;       │

&#x20;       ▼

Extract Dataset

&#x20;       │

&#x20;       ▼

Parse Graph Data

&#x20;       │

&#x20;       ▼

Load Nodes \& Relationships

&#x20;       │

&#x20;       ▼

Verify Data

&#x20;       │

&#x20;       ▼

Execute Benchmarks

&#x20;       │

&#x20;       ▼

Display Performance Metrics

```



\---



\## 📸 Screenshots



Add screenshots here.



Example:



\- Application Startup

\- CognoDB Browser

\- Benchmark Execution

\- Dataset Loading

\- Benchmark Results



\---



\## 📁 Project Structure



```

src

&#x20;├── benchmark

&#x20;├── config

&#x20;├── dataset

&#x20;├── loader

&#x20;├── manager

&#x20;├── metrics

&#x20;├── model

&#x20;├── service

&#x20;└── util

```



\---



\## 🚀 Future Enhancements



\- Export benchmark results to CSV

\- Interactive dashboard for benchmark visualisation

\- REST APIs to trigger benchmarks

\- Docker support

\- Performance comparison with Neo4j

\- Scheduled benchmark execution



\---



\## 👨‍💻 Author



\*\*Dharamsoth Pavan Nayak\*\*



\- GitHub: https://github.com/PAVANNAYAK45



\---



\## ⭐ If you found this project useful, consider giving it a star!

