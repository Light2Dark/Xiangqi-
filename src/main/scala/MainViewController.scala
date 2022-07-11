import scalafx.scene.control.{Button, TextField}
import scalafxml.core.macros.sfxml

@sfxml
class MainViewController(private val playButton: Button) {
  def handleStart(): Unit = {
    val resource = getClass.getResource("view/Game.fxml") // change to StartMenu later
    MainApp.showView(resource)
  }
}
