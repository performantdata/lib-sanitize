package com.performantdata

/** Data sanitization framework.
  *
  * Import the members of this package to use the framework:
  * @example
  * {{{
  * import com.performantdata.sanitize._
  *
  * case class TaxId(id: String)
  *
  * implicit val taxIdSanitizer = new Sanitizer[TaxId] {
  *   override def sanitize(t: TaxId) = …
  * }
  *
  * val t = TaxId("1234567")
  * System.out.println(t.sanitized)
  * }}}
  */
package object sanitize {
  implicit final class Sanitizable[A](private val a: A) extends AnyVal {
    def sanitized(implicit sanitizer: Sanitizer[A]): String = sanitizer.sanitize(a)
  }
}
