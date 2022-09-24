import org.scalatest.freespec.AnyFreeSpec

class SanitizerTest extends AnyFreeSpec {
  "The supplied standard library sanitizers" - {
    import com.performantdata.sanitize._

    "must have no effect on a Char" in {
      val c = 'X'
      assert(c.toString == c.sanitized)
    }
    "must have no effect on a Byte" in {
      val b = 10.toByte
      assert(b.toString == b.sanitized)
    }
    "must have no effect on an Int" in {
      val i = 2893
      assert(i.toString == i.sanitized)
    }
    "must have no effect on a Long" in {
      val l = 28985673321L
      assert(l.toString == l.sanitized)
    }
    "must have no effect on a Float" in {
      val f = 3.14f
      assert(f.toString == f.sanitized)
    }
    "must have no effect on a Double" in {
      val d = Math.PI
      assert(d.toString == d.sanitized)
    }
    "must have no effect on a String" in {
      val s = "Test string"
      assert(s == s.sanitized)
    }

    "must have no effect on a Option" in {
      val s = Some("Test string")
      val n: Option[String] = None
      assert(s.toString == s.sanitized)
      assert(n.toString == n.sanitized)
    }
    "must have no effect on an Either" in {
      val r = Right[Int, String]("Test string")
      val l = Left[Int, String](4322)
      assert(r.toString == r.sanitized)
      assert(l.toString == l.sanitized)
    }
  }
}
