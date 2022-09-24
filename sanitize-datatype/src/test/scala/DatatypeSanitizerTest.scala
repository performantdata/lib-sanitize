import com.performantdata.datatype._
import com.performantdata.names.NameExtractor
import org.scalatest.freespec.AnyFreeSpec
import shapeless.Generic.Aux
import shapeless.{::, Generic, HNil, Lazy}

/** Test of PII sanitization on Teledoc common datatypes. */
class DatatypeSanitizerTest extends AnyFreeSpec {
  import com.performantdata.sanitize._

  "The supplied standard library sanitizers (w/o the datatype sanitizers)" - {
    "must have no effect on a PersonalName" in {
      val p = PersonalName(GivenName("John"), Some(FamilyName("Smith")))

//      implicit val gnGeneric: Aux[GivenName, String :: HNil] =
//        Generic[GivenName]
//      implicit val gnSan: Lazy[Sanitizer[GivenName]] =
//        Sanitizer.genericSanitizer[GivenName, String :: HNil]
//
//      implicit val pnGeneric: Aux[PersonalName, GivenName :: Option[FamilyName] :: HNil] =
//        Generic[PersonalName]
//      implicit val pnSan: Lazy[Sanitizer[GivenName :: Option[FamilyName] :: HNil]] =
//        Lazy.mkLazy[Sanitizer[GivenName :: Option[FamilyName] :: HNil]]
//      implicit val sanitizer: Sanitizer[PersonalName] =
//        Sanitizer.genericSanitizer[PersonalName, GivenName :: Option[FamilyName] :: HNil]

      implicit val pnExtractor: NameExtractor[PersonalName] = new NameExtractor[PersonalName]

      assert(p.toString == p.sanitized)
    }
  }

  "The supplied datatype sanitizers" - {
    import com.performantdata.sanitize.datatype._

    "must sanitize a PersonalName" in {
      val p = PersonalName(GivenName("John"), Some(FamilyName("Smith")))
//      assert(s"${classOf[PersonalName].getSimpleName}(*-*)" == p.sanitized)
    }
  }
}
