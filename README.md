# KVStore-Java

KVStore-Java is a lightweight, in-memory key-value store implemented in Java. It is inspired by Redis and built for educational purposes, personal experimentation, and small-scale projects.

## Overview

This project is an attempt to understand and recreate the core mechanics of a key-value database system. It provides a CLI-based interface to interact with the store and optionally exposes an HTTP API via Spring Boot. The project also supports basic persistence using JSON snapshots and includes Docker support for easy distribution.

## Features

- Set and get keys with various value types (String, Integer, List, etc.)
- Time-to-live (TTL) support for keys
- Type-aware value wrappers
- JSON-based snapshot persistence
- Command-line interface for local interaction
- Optional HTTP server for RESTful access
- Docker-ready setup for deployment and sharing

## Getting Started

### Running in CLI Mode (Default)

```bash
java -jar kvstore.jar
# or if installed globally
kvstore start
