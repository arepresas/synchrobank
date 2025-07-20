# SynchroBank

A robust, self-hostable service to synchronize and aggregate your financial data from multiple institutions into a
single, unified interface.
## 📖 Overview

SynchroBank solves the problem of financial data fragmentation. In today's world, a person's financial life is spread
across multiple checking accounts, credit cards, investment platforms, and loan providers. This project provides a
powerful open-source engine to centralize all that information, offering a complete and unified view of your finances.

    For Developers: A reliable and well-documented backend service to power your next fintech application.

    For Power Users: A secure, self-hostable alternative to commercial services like Mint or Personal Capital, giving you full control and sovereignty over your own data.

    ⚠️ Disclaimer: This project, SynchroBank, is an independent open-source tool and is not affiliated with, endorsed by, or in any way officially connected with Synchrony Bank or any of its subsidiaries or affiliates.

## ✨ Key Features

    Multi-Institution Aggregation: Connect to multiple banks and credit card companies using the Plaid API (or alternative connectors).

    Automated Transaction Categorization: Intelligently categorize your spending to understand where your money goes.

    RESTful API: A clean, well-documented API to access your unified financial data from any client application.

    Security Built-in: Best practices for data encryption and secure authentication are implemented.

    Docker-Powered Deployment: Get up and running in minutes with a single docker-compose up command.

## 🏗️ Architecture Overview

```mermaid
graph TD
A[User Application / Frontend] -- REST API --> B{SynchroBank Service (Java/Spring)};
B -- API Calls --> C[Plaid / Financial Aggregator];
B -- Read/Write --> D[Database (PostgreSQL)];
C -- Financial Data --> B;
D -- Stored Data --> B;
```

## 🚀 Getting Started
## Prerequisites

Ensure you have the following tools installed before you begin:

    Java 21+ (if building from source)

    Apache Maven (if building from source)

    Docker and Docker Compose

### Installation

1. Using Docker (Recommended)

This is the quickest and easiest way to get SynchroBank running.

    Clone the repository:

    git clone https://github.com/arepresas/synchrobank.git
    cd synchrobank

    Start the services:

    docker-compose up -d

The API will be available at http://localhost:1080.

2. Building from Source

If you prefer to build the project manually:

    Ensure you meet the Java and Maven prerequisites.

    Set up the necessary environment variables (see Configuration section).

    Build the project and run tests:

    mvn clean install

    Run the application:

    java -jar target/synchrobank-0.0.1-SNAPSHOT.jar

## ⚙️ Configuration

SynchroBank is configured via environment variables.

For local development with docker database, all is already configured in application-local.yaml

## 👨‍💻 Development & Contribution

Interested in contributing? That's great! We welcome any help. Please read our CONTRIBUTING.md file (to be added soon) for details on our
code of conduct and the process for submitting pull requests.

To set up a development environment:

    Follow the steps in the "Building from Source" section.

    Using an IDE like IntelliJ IDEA or VS Code with Java and Maven support is recommended.

    Import the project as a Maven project.

### Running Tests:
To run the full suite of unit and integration tests, use the following Maven command:

mvn test

## 📜 License

This project is licensed under the MIT License. See the LICENSE file for details.
## 🙏 Acknowledgements & Contact

    Inspired by the need for open-source, user-controlled personal finance tools.

Have questions, feedback, or suggestions? Feel free to open an issue in the repository.