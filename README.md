# Java SE Revision

A hands-on Java SE revision project that walks through core Java concepts using a **camera brand/model catalogue** as the domain model.

---

## Tech Stack

| Tool / Library | Version | Purpose |
|---|---|---|
| Java | 17 | Core language |
| Maven | 3.x | Build & dependency management |
| Lombok | 1.18.30 | Boilerplate reduction (`@Data`, `@Builder`, etc.) |

---

## Architecture

The project is a single Maven module (`Lab1Java`) with one package – `Lab1`.

```
src/main/java/Lab1/
├── Brand.java      # Domain entity – camera brand (name, country, value, year, models)
├── Model.java      # Domain entity – camera model (name, price, announce year, brand ref)
├── ModelDto.java   # DTO – flat representation of a model used for sorted output
└── Main.java       # Entry point – runs all demonstration tasks sequentially
```

Both `Brand` and `Model` implement `Comparable` (alphabetical by name) and `Serializable` (for file I/O). `ModelDto` implements `Comparable` with a two-level sort: brand name first, then model name.

---

## What It Covers

The `Main` class runs a series of numbered tasks that demonstrate different Java SE features:

| Task | Concept demonstrated |
|---|---|
| **Task 2** | Building a `TreeMap<String, Brand>` and populating nested `LinkedList<Model>` collections using the Lombok `@Builder` pattern |
| **Task 3** | Collecting all models into a `Set<Model>` with `Stream.flatMap()` |
| **Task 4** | Filtering models whose names start with `"A"` or `"C"`, then sorting by price descending |
| **Task 5** | Mapping domain objects to `ModelDto` and sorting via `Comparable` |
| **Task 6** | Serialising `Brand` objects to a binary file (`object.obj`) with `ObjectOutputStream`, then deserialising them back with `ObjectInputStream`; demonstrates exception handling (`EOFException`, `IOException`) |
| **Task 7** | Parallel processing with a custom `ForkJoinPool(3)` and `parallelStream()`, including a 1-second simulated delay per item |

---

## How to Run

### Prerequisites
- JDK 17+
- Maven 3.6+

### Build & Run

```bash
# Clone the repository
git clone https://github.com/marziel2001/Java-se-revision.git
cd Java-se-revision

# Compile
mvn compile

# Run the main class
mvn exec:java -Dexec.mainClass="Lab1.Main"
```

> The program writes a `object.obj` binary file to the working directory during Task 6 and reads it back immediately after.

---

## Output Structure

Each task prints a labelled section to standard output, for example:

```
TASK 2
Brand(name=Canon, ...)
  Model(name=EOS 5D Mark IV, price=2499.0, ...)
  ...
TASK 3
...
TASK 7   ← parallel, order may vary between runs
```
