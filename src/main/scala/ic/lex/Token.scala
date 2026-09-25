package ic.lex

import scup.{Pos, Tokenish}

/**
 * The different kinds of tokens in IC.
 *
 * TODO: add a case for every kind of token in the IC language:
 * keywords, identifiers, literals, operators, and punctuation.
 */
enum TokenKind:
  case LPAREN, RPAREN

/**
 * A token produced by the Lexer.
 *
 *  - kind: which kind of token this is.
 *  - text: the token's text.  For most tokens this is exactly the
 *    characters matched in the source; for string literals it should
 *    be the string's *contents*, with escape sequences like \n already
 *    processed.
 *  - pos:  where the token started in the source.
 *
 * Extending scup.Tokenish (which just requires pos) is what will
 * let a parser consume a sequence of Tokens in PA 2.
 */
case class Token(kind: TokenKind, text: String, pos: Pos) extends Tokenish:

  /** The line on which this token appeared. */
  def line: Int = pos.line

  override def toString: String = s"[$kind,$text,$line]"
