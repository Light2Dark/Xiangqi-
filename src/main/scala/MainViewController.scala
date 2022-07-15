import scalafx.scene.control.{Button, TextField}
import scalafxml.core.macros.sfxml

@sfxml
class MainViewController(private val playButton: Button) {
  def handleStart(): Unit = {
    val resource = getClass.getResource("view/StartMenu.fxml") // change to Game.fxml for quick testing
    MainApp.showView(resource)
  }

  def handlePlay() = {
    val resource = getClass.getResource("view/HowToPlay.fxml")
    MainApp.showView(resource)
  }

  def handleExit() = {
    System.exit(0)
  }
}
