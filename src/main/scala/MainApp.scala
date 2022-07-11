import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.Label
import scalafx.scene.layout.{AnchorPane, BorderPane, VBox}
import scalafxml.core.{FXMLLoader, FXMLView, NoDependencyResolver}
import scalafx.Includes._
import javafx.{scene => jfxs}
import scalafx.scene.image.Image

object MainApp extends JFXApp {

  val rootResource = getClass.getResource("view/RootLayout.fxml")
  val loader = new FXMLLoader(rootResource, NoDependencyResolver)
  loader.load()
  val roots = loader.getRoot[jfxs.layout.BorderPane]

  stage = new PrimaryStage {
    title = "Xiangqi (象棋)"
    icons += new Image(getClass.getResourceAsStream("resources/images/logo.png"))
    resizable = false // cannot resize because moving the chess pieces based on mouse events relative to mouse source seem to be buggy compared to moving relative to screen x and y positions
    scene = new Scene {
      root = roots
      stylesheets = List(getClass.getResource("view/styles.css").toString())
    }
  }

  def showView(resource: java.net.URL) = {
    val resourceLoader = new FXMLLoader(resource, NoDependencyResolver)
    resourceLoader.load()

    val viewRoots = resourceLoader.getRoot[jfxs.layout.AnchorPane]
    roots.setCenter(viewRoots)
  }

  val mainView = getClass.getResource("view/MainView.fxml")
  showView(mainView)
}