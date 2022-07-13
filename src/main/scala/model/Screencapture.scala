package model

import scalafx.embed.swing.SwingFXUtils
import scalafx.scene.Node
import javax.imageio.ImageIO
// import scala.reflect.io.File
import java.io.File

// inspired from https://stackoverflow.com/questions/54417430/how-to-convert-scalafx-root-to-image-and-then-save-it-in-local
// https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#snapshot-javafx.scene.SnapshotParameters-javafx.scene.image.WritableImage-
object Screencapture {
  def takeScreenshot(node: Node, file: File) = {
    val image = node.snapshot(null, null) // have to use null here
    val bufferedImage = SwingFXUtils.fromFXImage(image, null) // convert img to buffered image
    assert(bufferedImage ne null)

    ImageIO.write(bufferedImage, "png", file)
  }
}
