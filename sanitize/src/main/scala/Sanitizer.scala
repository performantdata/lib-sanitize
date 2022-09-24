package com.performantdata.sanitize

import shapeless._

import scala.reflect.runtime.universe

/** A typeclass for types that can be sanitized of their confidential data.
  *
  * @tparam A a type that can be sanitized
  */
abstract class Sanitizer[A: universe.WeakTypeTag] {
  /** Return a sanitized representation of the given instance, with confidential data removed. */
  def sanitize(a: A): String
}

object Sanitizer {
  private[this] def instance[A](f: A => String): Sanitizer[A] = new Sanitizer[A] {
    override def sanitize(a: A): String = f(a)
  }

  implicit val charSanitizer: Sanitizer[Char] = instance[Char](_.toString)
  implicit val byteSanitizer: Sanitizer[Byte] = instance[Byte](_.toString)
  implicit val intSanitizer: Sanitizer[Int] = instance[Int](_.toString)
  implicit val longSanitizer: Sanitizer[Long] = instance[Long](_.toString)
  implicit val floatSanitizer: Sanitizer[Float] = instance[Float](_.toString)
  implicit val doubleSanitizer: Sanitizer[Double] = instance[Double](_.toString)
  implicit val stringSanitizer: Sanitizer[String] = instance[String](identity)
  //TODO Create Sanitizers for other common types: date/times, locales,…


  implicit def optionSanitizer[A: Sanitizer]: Sanitizer[Option[A]] = instance[Option[A]](_.map(_.sanitized).toString)

  implicit def eitherSanitizer[A: Sanitizer, B: Sanitizer]: Sanitizer[Either[A, B]] =
    instance[Either[A, B]](_.map(_.sanitized).swap.map(_.sanitized).swap.toString)

  //TODO Create Sanitizers for other common containers: trys, lists, sets, maps,…


  implicit val hnilSanitizer: Sanitizer[HNil] = instance[HNil](_ => "")

  implicit def hlistSanitizer[H, T <: HList](implicit hSan: Lazy[Sanitizer[H]], tSan: Sanitizer[T]): Sanitizer[H :: T] =
    new Sanitizer[H :: T] {
      override def sanitize(a: H :: T): String = a match { case h :: t =>
        val tail = tSan.sanitize(t)
        val space = if (tail.isEmpty) "" else ", "
        s"${hSan.value.sanitize(h)}$space$tail"
      }
    }

  implicit def genericSanitizer[A, R](implicit gen: Generic.Aux[A,R], san: Lazy[Sanitizer[R]]): Sanitizer[A] =
    new Sanitizer[A] {
      override def sanitize(a: A): String = {
        val ttag = implicitly[universe.WeakTypeTag[A]]
        s"${ttag.tpe}(${san.value.sanitize(gen.to(a))})"
      }
    }
}
