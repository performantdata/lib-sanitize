package com.performantdata.sanitize

import com.performantdata.datatype.PersonalName

package object datatype {
  implicit val personalNameSanitizer: Sanitizer[PersonalName] =
    (_: PersonalName) => s"${classOf[PersonalName].getSimpleName}(*-*)"
}
