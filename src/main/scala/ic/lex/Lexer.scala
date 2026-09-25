package ic.lex

import slex.{LexError, LexSpec}
import ic.error.LexicalError

/**
 * The IC lexer.  The token language lives in the spec file `ic.slex`
 * at the root of this project (plus the TokenKind enum next door) --
 * that is where your work goes; this class is the host shim, provided
 * in full.  It loads the spec once and converts slex's LexError into
 * the compiler's LexicalError.
 *
 * Use the lexer either one token at a time:
 *
 *   val lex = new Lexer(source)
 *   var t = lex.next()
 *   while t.isDefined do { ...; t = lex.next() }
 *
 * or all at once:
 *
 *   val toks = new Lexer(source).tokenize()
 */
class Lexer(source: String):

  // The input is scanned one token per next() call, so every token
  // before a bad lexeme is produced before that lexeme's
  // LexicalError is thrown.
  private val scan = Lexer.spec.scan(source)

  /** The next token, or None at the end of the input. */
  def next(): Option[Token] =
    try scan.next().map(t => Token(t.kind, t.text, t.pos))
    catch case LexError(msg, pos) => throw LexicalError(pos.line, pos.col, msg)

  /** Lex the whole input. */
  def tokenize(): IndexedSeq[Token] =
    val toks = Vector.newBuilder[Token]
    var t = next()
    while t.isDefined do
      toks += t.get
      t = next()
    toks.result()

object Lexer:

  /** The spec-driven rule table (loaded once per JVM). */
  val spec: LexSpec[TokenKind] = LexSpec.load[TokenKind]("ic.slex")
