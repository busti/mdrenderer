import better.files._
import File._
import java.io.{File => JFile, _}

import laika.api.Transform
import laika.format.{HTML, Markdown}
import laika.markdown.github.GitHubFlavor

object Main extends App {
  implicit def better2java(file: File): JFile = file.toJava

  val static = currentWorkingDirectory / "static"
  val pregen = static / "pregen"
  val input  = static / "input"
  val output = static / "output"

  input.clear()
  output.clear()
  pregen.copyTo(input)

  Transform.
    from(Markdown)
    .to(HTML)
    .using(GitHubFlavor)
    .fromDirectory(input)
    .toDirectory(output)
}
