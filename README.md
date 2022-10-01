# lib-sanitize
Scala library for sanitizing confidential information from string representations.

In practice, this is directed particularly at redacting [PII][PII] and [PHI][PHI] from strings
that are sent to [logging][logging] facilities.
Since it's usually desirable to make the collected logs of a software system available widely to engineers
for the purpose of debugging,
and also required to restrict access to the confidential information,
this provides a facility to assist software engineers in writing code that only logs redacted data.
It does so by providing implementations of a typeclass for all Scala ADTs,
which can be overridden with a custom implementation for every type that requires redaction.

For a usage example, consult the package documentation, which can be compiled with `sbt doc` as usual.

## developing
This repo uses a package prefix structure for its source code.
In IntelliJ, when loading the project as a BSP project,
you will need to [manually edit the package prefixes][package-prefix] for the source directories,
since there seems to be a bug in IDEA at this time that doesn't recognize the corresponding `sbt` settings.

[logging]: https://en.wikipedia.org/wiki/Logging_(software)
[package-prefix]: https://blog.jetbrains.com/scala/2020/11/26/enhanced-package-prefixes/#Package_prefix
[PII]: https://en.wikipedia.org/wiki/Personal_data
[PHI]: https://en.wikipedia.org/wiki/Protected_health_information
