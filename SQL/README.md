SQL Projects with Java

This repository contains two educational projects in Java focused on learning how to use SQL in applications.
The goal is to present both a basic approach with JDBC and SQLite, and a more structured implementation with DAO, JPA/Hibernate, and MySQL.

Project 1: JDBC with SQLite

Demonstrates direct database interaction using JDBC.

Works with a simple SQLite file-based database (usersdb.sqlite3) and a users table.

Shows how to run queries such as SELECT and INSERT.

Includes an insecure query example (vulnerable to SQL Injection) for educational purposes.

Shows how to secure queries using PreparedStatement.

Objective: Test the use of an SQLite file database and evaluate its performance compared to traditional server-based databases.

Project 2: DAO with JPA/Hibernate and MySQL

Demonstrates a professional and structured way of working with databases.

Uses DAO (Data Access Object) to separate business logic from persistence.

Implements JPA/Hibernate as an ORM framework.

Works with MySQL as the database.

Includes entity mapping, DAO implementation, and transaction handling with EntityManager.

Requirements

Java 8 or higher

SQLite (for Project 1)

MySQL (for Project 2)

Maven dependencies:

sqlite-jdbc

hibernate-core

mysql-connector-j

Educational Goal

Understand database interaction from Java using raw JDBC and experiment with SQLite file databases.

Apply a more scalable and maintainable solution with DAO and JPA/Hibernate.

Italiano
Progetti SQL con Java

Questo repository contiene due progetti educativi in Java per imparare a usare SQL nelle applicazioni.
L’obiettivo è mostrare sia un approccio di base con JDBC e SQLite, sia un’implementazione più strutturata con DAO, JPA/Hibernate e MySQL.

Progetto 1: JDBC con SQLite

Dimostra l’interazione diretta con il database tramite JDBC.

Utilizza un database SQLite basato su file (usersdb.sqlite3) e una tabella users.

Mostra come eseguire query come SELECT e INSERT.

Include un esempio di query insicura (vulnerabile a SQL Injection) a scopo didattico.

Mostra come rendere sicure le query usando PreparedStatement.

Obiettivo: Sperimentare l’uso di un database SQLite su file e valutarne le prestazioni (performance) rispetto ai database tradizionali basati su server.

Progetto 2: DAO con JPA/Hibernate e MySQL

Dimostra un approccio più professionale e strutturato alla gestione del database.

Utilizza il pattern DAO (Data Access Object) per separare la logica di business dalla persistenza.

Implementa JPA/Hibernate come framework ORM.

Utilizza MySQL come database.

Include mapping delle entità, implementazione di DAO e gestione delle transazioni con EntityManager.

Requisiti

Java 8 o superiore

SQLite (per il Progetto 1)

MySQL (per il Progetto 2)

Dipendenze Maven/Gradle:

sqlite-jdbc

hibernate-core

mysql-connector-j

Obiettivo didattico

Capire come interagire con un database da Java usando JDBC puro e sperimentare con database SQLite su file.

Applicare una soluzione più scalabile e manutenibile con DAO e JPA/Hibernate.
