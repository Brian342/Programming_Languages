# Cypher Tool

A Java command-line application that encrypts and decrypts messages using multiple classical ciphers.

## Features

- Encrypt messages
- Decrypt messages
- ROT13 Cipher
- Atbash Cipher
- Caesar Cipher (Shift of 3)
- Preserves spaces, punctuation, and numbers
- Input validation for user selections

---

# Prerequisites

Before running the project, install:

- Java JDK 21 (or later)
- Git (optional, if cloning the repository)

---

# Installing Java

## macOS

### Step 1: Check if Java is installed

Open **Terminal** and run:

```bash
java --version
javac --version
```

If both commands return a Java version, Java is already installed.

---

### Step 2: Install Homebrew (if not already installed)

Run:

```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

Verify installation:

```bash
brew --version
```

---

### Step 3: Install JDK 21

```bash
brew install openjdk@21
```

or install Oracle JDK 21 from:

https://www.oracle.com/java/technologies/downloads/

---

### Step 4: Verify installation

```bash
java --version
javac --version
```

Expected:

```
java 21.x.x
javac 21.x.x
```

---

# Windows

## Step 1: Download Java

Download Java JDK 21 from Oracle:

https://www.oracle.com/java/technologies/downloads/

or

Adoptium:

https://adoptium.net/

Choose:

- Windows x64 Installer (.msi)

---

## Step 2: Install

Run the installer and complete the setup.

Leave all default options selected.

---

## Step 3: Verify installation

Open Command Prompt and run:

```cmd
java --version
javac --version
```

Expected:

```
java 21.x.x
javac 21.x.x
```

---

# Linux (Ubuntu/Debian)

Update packages:

```bash
sudo apt update
```

Install OpenJDK 21:

```bash
sudo apt install openjdk-21-jdk
```

Verify installation:

```bash
java --version
javac --version
```

---

# Installing Git

## macOS

```bash
brew install git
```

Verify:

```bash
git --version
```

---

## Windows

Download Git:

https://git-scm.com/downloads

Verify:

```cmd
git --version
```

---

## Linux

```bash
sudo apt install git
```

---

# Clone the Repository

Clone the project:

```bash
git clone <repository-url>
```

Navigate into the project folder:

```bash
cd cypher
```

---

# Project Structure

```
cypher/
│
├── CypherTool.java
├── InputData.java
└── README.md
```

---

# Compiling the Project

Compile all Java files.

### macOS / Linux

```bash
javac *.java
```

### Windows

```cmd
javac *.java
```

Compilation generates the `.class` files.

---

# Running the Project

### macOS / Linux

```bash
java CypherTool
```

### Windows

```cmd
java CypherTool
```

---

# Example

```
Welcome to the Cypher Tool!

Select operation:
1. Encrypt
2. Decrypt

:> 1

Select Cypher:
1. ROT13
2. Atbash
3. Caesar Cipher

:> 3

Enter your message:
Hello World!

Khoor Zruog!
```

---

# Supported Ciphers

## ROT13

Shifts every letter by 13 positions.

Example:

```
Hello
```

↓

```
Uryyb
```

---

## Atbash

Maps every letter to its opposite in the alphabet.

Example:

```
Hello
```

↓

```
Svool
```

---

## Caesar Cipher

Shifts every alphabetic character three positions.

Example:

```
Hello
```

↓

```
Khoor
```

Decrypting reverses the shift by three positions.

---

# Development Workflow

After making changes:

Compile:

```bash
javac *.java
```

Run:

```bash
java CypherTool
```

---

# Git Commands

Check repository status:

```bash
git status
```

Stage changes:

```bash
git add .
```

Commit changes:

```bash
git commit -m "Describe your changes"
```

Push to GitHub:

```bash
git push
```

Pull the latest changes:

```bash
git pull
```

---

# Troubleshooting

## `javac` is not recognized

Java JDK is not installed or not added to your PATH.

Verify:

```bash
javac --version
```

---

## `java` is not recognized

Install Java and restart your terminal.

---

## `Could not find or load main class CypherTool`

Compile the project first:

```bash
javac *.java
```

Then run:

```bash
java CypherTool
```

---

## Changes don't appear

Remember to compile again after editing any Java file:

```bash
javac *.java
```

---

# Contributors

- ROT13 Implementation
- Atbash Implementation
- Caesar Cipher Implementation