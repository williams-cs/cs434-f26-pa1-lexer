#!/bin/bash
#
# Run the IC compiler (ic.Compiler) on a source file:
#
#   ./icc.sh [-d] file.ic [flags ...]
#
# Arguments are passed to ic.Compiler verbatim, in its own
# convention: optional -d first, then the file, then any other flags
# (e.g. ./icc.sh test/test1.ic -printAST).  sbt runs at warn log
# level: only warnings and errors are shown.  The script's own
# progress lines are prefixed with "[ICC] ".

DFLAG=""
if [ "$1" = "-d" ]; then
    DFLAG="-d"
    shift
fi
FILE="$1"
shift

if [ -z "$FILE" ]; then
    echo "usage: $0 [-d] file.ic [flags ...]" >&2
    exit 1
fi

# The script cds into the project directory before running sbt, so a
# path given relative to the caller's directory must be made absolute
# first.
case "$FILE" in
    /*) ;;
    *) FILE="$PWD/$FILE" ;;
esac

cd "$(dirname "$0")" || exit 1
echo "[ICC] Updating icc"
echo "[ICC] Compiling $FILE"
sbt --warn --supershell=never "run ${DFLAG:+$DFLAG }$FILE $*" || exit 1
echo "[ICC] Done"
