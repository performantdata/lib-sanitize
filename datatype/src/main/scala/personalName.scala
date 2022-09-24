package com.performantdata.datatype

/** A given name of an individual.
  *
  * The name given to an individual, usually at or near birth,
  * to distinguish that individual from others in its family or neighborhood.
  */
final case class GivenName(string: String) extends AnyVal

/** A family name of an individual.
  *
  * The name part given to an individual, usually at or near birth,
  * that is shared with other members of its family.
  *
  * Not all cultures utilize family names—Indonesian culture is a notable counterexample.
  */
final case class FamilyName(string: String) extends AnyVal

/** A personal name.
  *
  * The name of an individual, human or otherwise (though usually human).
  */
final case class PersonalName(givenName: GivenName, familyName: Option[FamilyName]) {
//  @deprecated("This is a bad implementation, just here as an example.")
//  override def toString: String =
//    s"${classOf[PersonalName].getSimpleName}(${givenName.string}${familyName.map(n => s" ${n.string}").getOrElse("")})"
}
