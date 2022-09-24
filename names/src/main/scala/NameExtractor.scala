package com.performantdata.names

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

/** An object that extracts the name from a type parameter.
  *
  * @tparam A the type whose name is returned
  */
class NameExtractor[A] {
  import NameExtractor._

  /** Return the class name of the type parameter. */
  def name: String = macro name_impl[A]
}

object NameExtractor {
  implicit def extractor[A]: NameExtractor[A] = new NameExtractor[A]

  def name_impl[A](c: blackbox.Context)(implicit ttag: c.WeakTypeTag[A]): c.Expr[String] = {
    import c.universe._
    val name = ttag.tpe.dealias.toString.split('.').last
    c.Expr[String](q"$name")
  }
}
