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
  /** A decorator that gives all objects a method for returning a sanitized version of themselves.
    *
    * Since there are no constraints on the type `A`, every object is decorated by this class's method
    * when this implicit is in scope, making the [[sanitized]] method available on every such object.
    *
    * @param a an instance of the sanitizable class, to be sanitized
    * @tparam A the sanitizable class
    */
  implicit final class Sanitizable[A](private val a: A) extends AnyVal {
    /** Return a sanitized string representation of this object.
      *
      * The representation conforms to some standard of data safety that isn't specified here
      * but rather in the custom, overriding [[Sanitizer]] implementations that you write.
      *
      * @param sanitizer a typeclass instance that makes this `A` instance sanitizable
      */
    def sanitized(implicit sanitizer: Sanitizer[A]): String = sanitizer.sanitize(a)
  }
}
