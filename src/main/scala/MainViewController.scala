import scalafx.scene.control.Button
import scalafxml.core.macros.sfxml

@sfxml
class MainViewController(private val playButton: Button) {
  def handleStart(): Unit = {
    val resource = getClass.getResource("view/StartMenu.fxml")
    MainApp.showView(resource)
  }
}
