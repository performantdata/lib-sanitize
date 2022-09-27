import com.performantdata.datatype._
import org.scalatest.freespec.AnyFreeSpec

/** Test of PII sanitization on Teledoc common datatypes. */
class DatatypeSanitizerTest extends AnyFreeSpec {
  import com.performantdata.sanitize._

  "The supplied standard library sanitizers (w/o the datatype sanitizers)" - {
    "must have behave like toString on a simple case class" in {
      val n = GivenName("John")
      assert(n.toString == n.sanitized)
    }
    "must have behave like toString on a nested case class" in {
      val p = PersonalName(GivenName("John"), Some(FamilyName("Smith")))
      assert(p.sanitized == p.toString)
    }
  }

  "The supplied datatype sanitizers" - {
    import com.performantdata.sanitize.datatype._

    "must sanitize a PersonalName" in {
      val p = PersonalName(GivenName("John"), Some(FamilyName("Smith")))
      assert(p.sanitized == s"${classOf[PersonalName].getSimpleName}(*-*)")
    }
  }
}
