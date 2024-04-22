package edu.trinity.videoquizreact

import shared.SharedMessages
import org.scalajs.dom
import org.scalajs.dom.html
import org.scalajs.dom.document

import slinky.core._
import slinky.web.ReactDOM
import slinky.web.html._
import scala.scalajs.js.annotation.JSExportTopLevel

object ScalaJSExample {

  def main(args: Array[String]): Unit = {
    // This line demonstrates using Scala.js to modify the DOM.
    dom.document.getElementById("scalajsShoutOut").textContent = SharedMessages.itWorks

    // What is below is using Scala.js with Slinky to use React.
    println("Call the react stuff.")
    ReactDOM.render(
      div(
        h1("Hello, world!"),
        p("This is a component added with Slinky, a Scala.js React binding.")
      ),
      dom.document.getElementById("root")
    )

    document.getElementById("title").innerHTML = "This is set with scalajs"
    document.getElementById("content").innerHTML = "hello"

    appendParagraph(document.getElementById("content"), "this si done with a function")
    drawToCanvas(document.getElementById("canvas").asInstanceOf[html.Canvas])
  }
  def appendParagraph(target: dom.Node, text: String): Unit = {
    val p = document.createElement("p")
    val textNode = document.createTextNode(text)
    p.appendChild(textNode)
    target.appendChild(p)
  }
  //js export specifies name it gets compiled to js to, needed for html names talking to names here 
  @JSExportTopLevel("doClickStuff")
  def doClickStuff(): Unit = {
      println("button clicked")
  }

  def drawToCanvas(canvas: html.Canvas): Unit = {
      val context = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
      context.fillRect(100, 100, 200, 150)

  }
}
