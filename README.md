# IC Compiler

#### Group Members



## Overview

...

## Building and Testing

    sbt compile                   compile everything
    sbt test                      run the MUnit unit tests
    ./icc.sh [-d] <file.ic>       run the compiler on one file
    sbt "specTests tests/pa1"     run a test suite (add -v for details)

`./icc.sh` is a thin wrapper around `sbt "run ..."` that hides sbt's
log messages; it takes the same arguments as the compiler.
