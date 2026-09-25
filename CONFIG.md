
### Directory Structure

    CONFIG.md     this file
    README.md     Your write-up.
    build.sbt     the sbt build definition
    ./src         all source code
    ./test        put your test files in this directory

### Building

**In VS Code (with Metals) or IntelliJ:**

- Open the project folder; the editor imports `build.sbt` automatically
  and rebuilds whenever you save a file.

**In a shell:**

- run `sbt compile` to build everything
- run `sbt test` to run the unit tests
- run `sbt "specTests <dir>"` to run the specification tests (see below)
- run `sbt assembly` to build a standalone `target/icc.jar`
- run `sbt clean` to remove generated files

### Running

**In VS Code / IntelliJ**: use the `run` code lens on `ic.Compiler`,
or create a Run Configuration with main class `ic.Compiler` and program
arguments like `test/test1.ic`.

**In a shell:** Run:

```
sbt "run test/test1.ic"
```

or, after `sbt assembly`:

```
java -jar target/icc.jar test/test1.ic
```

### Testing

`sbt test` runs the MUnit unit tests in `src/test/scala`.

To run the compiler on a whole directory of `.ic` test files and
compare its output to the matching `.ic.expected` files, use:

```
sbt "specTests tests/pa1"       # run every test in tests/pa1/
sbt "specTests -v tests/pa1"    # also dump each failing test
```

It reports how many tests passed and failed, and leaves each test's
output in a `.ic.output` file next to it.  The runner itself is
`src/test/scala/tests/SpecTests.scala` --- extend it as you like.

### Git

**In VS Code / IntelliJ**: both editors have built-in git support.

**In a shell:** Use the standard `git` command as usual.
