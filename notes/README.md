# Notes

A simple, interactive command-line tool for managing short, single-line notes
organized into named **collections**. Each collection is stored in its own
plain-text file, so your notes persist between runs.

## Key features

- **Interactive menu** — show, add, and delete notes without re-running the
  command for every action.
- **Collections** — organize notes into separate named groups (e.g. `packing_list`,
  `groceries`, `todo`), each backed by its own file.
- **Persistence** — notes are saved to disk immediately after every add/delete,
  so nothing is lost between sessions.
- **Input validation** — invalid menu choices, empty notes, and out-of-range
  delete numbers are handled gracefully with clear error messages instead of
  crashing.
- **Built-in help** — `-h` / `--help` prints usage instructions.

## Prerequisites

- **Java 11 or later** (uses the single-file source-code launcher, so no
  separate compilation step is required).

Check your version with:

```bash
java -version
```

## Installation

No build step needed — just clone the repo and run the file directly with `java`.

```bash
git clone <this-repo-url>
cd notes-cli
```

## Usage

```
java Notes.java [COLLECTION]
java Notes.java -h | --help
```

- `[COLLECTION]` — the name of the collection to open or create (e.g. `packing_list`).
  Notes are stored in `[COLLECTION].txt` in the current directory.
- `-h`, `--help` — show usage information and exit.

Running with no arguments, too many arguments, or `-h`/`--help` prints:

```
$> java Notes.java --help
Usage: java Notes.java [COLLECTION]

This tool allows users to manage short single-line notes within a collection.

Options:
-h, --help       Show this help message and exit
[COLLECTION]     The name of the collection to manage
```

### Example session

```
$> java Notes.java packing_list
Welcome to the notes tool!

Collection: Packing List

Select operation:

1. Show notes
2. Add a note
3. Delete a note
4. Exit
$> 1

Notes:
001 - delorean
002 - flux capacitor
003 - brain-wave analyzer helmet
004 - OUTATIME license plate

---

Select operation:

1. Show notes
2. Add a note
3. Delete a note
4. Exit
$> 2

Enter the note:
$> self-lacing shoes

"self-lacing shoes" added to Packing List

---

Select operation:

1. Show notes
2. Add a note
3. Delete a note
4. Exit
$> 3

Enter the number of the note to remove or 0 to cancel:
001 - delorean
002 - flux capacitor
003 - brain-wave analyzer helmet
004 - OUTATIME license plate
005 - self-lacing shoes
$> 003

"brain-wave analyzer helmet" deleted from Packing List

---

Select operation:

1. Show notes
2. Add a note
3. Delete a note
4. Exit
$> 4

Bye!
```

Running the tool again with the same collection name (`java Notes.java packing_list`)
will load the notes that were saved from the previous session.

## Error handling

- Selecting an option outside `1`–`4` prints an error and re-shows the menu.
- Adding an empty note is rejected with a message; nothing is written to disk.
- Deleting with a non-numeric or out-of-range value prints an error instead
  of crashing, and no note is removed.
- If the collection file can't be read or written (e.g. permissions issue),
  a warning/error is printed and the tool continues running rather than
  terminating.
