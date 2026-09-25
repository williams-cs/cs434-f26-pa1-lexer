package ic

import ic.lex.Lexer
import ic.error.LexicalError
import java.io.IOException

/**
 * The main class for the IC Compiler.  The Compiler
 * expects the name of the file to process on the command line,
 * as in:
 * <pre>
 *   sbt "run test/test1.ic"      (or: java -jar target/icc.jar test/test1.ic)
 * </pre>
 * An optional flag <tt>-d</tt> can be provided, as in:
 * <pre>
 *   sbt "run -d test/test1.ic"
 * </pre>
 * In this case, messages created by calling {@link ic.Util#debug(String)}
 * or {@link ic.Util#debug(String, Object...)} will be printed to the
 * terminal.  If <tt>-d</tt> is not provided, these messages will
 * be silently ignored.
 */
object Compiler {

  def main(args: Array[String]): Unit = {

    var n = 0;

    // If first command line argument is -d, turn on debugging
    if (args.length > 0 && args(n).equals("-d")) {
      Util.debug = true;
      n = 1;
    }

    // Get name of file
    if (args.length == n) {
      System.out.println("No file given.");
    } else {
      val file = args(n);

      // example of debug message: This message will only be printed if
      // you provide "-d" on the command line.
      Util.debug("Processing %s...", file);

      // TODO: finish me.  Print each token on its own line, followed by
      // "Success." at the end of the input.  You'll want to catch
      // LexicalErrors here, print them, and print "Failed." instead.
      val source = scala.io.Source.fromFile(file).mkString
      val lex = new Lexer(source)
      val t = lex.next()          // the next token, or None at the end
      println(t.get)
    }
  }
}
