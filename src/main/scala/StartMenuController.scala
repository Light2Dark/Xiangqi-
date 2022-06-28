import scalafx.scene.control.Button
import scalafxml.core.macros.sfxml

@sfxml
class StartMenuController(private val startGameButton: Button) {
  def startGame() = {
    val resource = getClass.getResource("view/Game.fxml")
    MainApp.showView(resource)
  }
}
