import scalafxml.core.macros.sfxml

@sfxml
class RootController {
  def handleHelp() = {
    val resource = getClass.getResource("view/HowToPlay.fxml")
    MainApp.showView(resource)
  }

  def handlePlay() = {
    val resource = getClass.getResource("view/StartMenu.fxml")
    MainApp.showView(resource)
  }

  def handleExit() = {
    System.exit(0)
  }
}

