import model.{ChessBoard, Player}
import scalafx.scene.control.{Alert, Button, TextField}
import scalafxml.core.macros.sfxml

@sfxml
class StartMenuController(private val startGameButton: Button, val playerOneInput: TextField, val playerTwoInput: TextField) {

  def nullChecking (x : String): Boolean = x == null || x.length == 0

  // derived from practical class notes
  def hasValidInput(): Boolean = {
    var errorMessage = ""
    if (nullChecking(playerOneInput.text.value))
      errorMessage += "No valid player 1 name\n"
    if (nullChecking(playerTwoInput.text.value))
      errorMessage += "No valid player 2 name\n"

    if (errorMessage.length() == 0) {
      return true
    } else {
      // Show the error message.
      val alert = new Alert(Alert.AlertType.Error){
        initOwner(MainApp.stage)
        title = "Invalid Fields"
        headerText = "Please correct invalid fields"
        contentText = errorMessage
      }.showAndWait()

      return false;
    }

  }

  def startGame() = {
    val resource = getClass.getResource("view/Game.fxml")

    if (hasValidInput()) {
      ChessBoard.playerOne.name = playerOneInput.text.value
      ChessBoard.playerTwo.name = playerTwoInput.text.value
      MainApp.showView(resource)
    }
  }

}
