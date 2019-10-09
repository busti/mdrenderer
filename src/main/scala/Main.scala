import better.files._
import File._
import java.io.{File => JFile, _}

import laika.api.Transform
import laika.format.{HTML, Markdown}
import laika.markdown.github.GitHubFlavor

object Main extends App {
  implicit def better2java(file: File): JFile = file.toJava

  val static  = currentWorkingDirectory / "static"
  val pregen  = static / "pregen"
  val input   = static / "input"
  val userdef = input  / "userdef"
  val output  = static / "output"

  userdef.clear()
  output.clear()
  pregen.copyTo(userdef)

  Transform.
    from(Markdown)
    .to(HTML)
    .using(GitHubFlavor)
    .fromDirectory(input)
    .toDirectory(output)
}
