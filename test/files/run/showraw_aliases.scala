import scala.reflect.runtime.universe._
import scala.tools.reflect.ToolBox

object Test extends App {
  val tb = runtimeMirror(getClass.getClassLoader).mkToolBox()
  val tree = tb.parse("""
    import scala.reflect.runtime.{universe => ru}
    ru
  """)
  val ttree = tb.typeCheck(tree)

  def stabilize(s: String) = """#\d+""".r.replaceAllIn(s, "#<id>")
  println(showRaw(ttree))
  println(stabilize(showRaw(ttree, printIds = true)))
}