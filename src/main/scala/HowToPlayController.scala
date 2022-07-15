import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.{Label, Pagination}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.{Priority, VBox}
import scalafxml.core.macros.sfxml
import scalafx.Includes._
import scalafx.scene.layout.Priority.Always

@sfxml
class HowToPlayController(var paginationLayout: VBox) {
  def handleBack() = {
    val resource = getClass.getResource("view/MainView.fxml")
    MainApp.showView(resource)
  }

  // Pagination handling
  // inspired by scala ensemble package

  paginationLayout.vgrow = Always
  paginationLayout.hgrow = Always
  paginationLayout.alignment = Pos.Center
  paginationLayout.spacing = 10
  paginationLayout.padding = Insets(20)

  // Images to load pages, until doesnt include end
  val images = for (i <- 1 until 9) yield {
    val ipStream = this.getClass.getResourceAsStream("resources/images/howtoplay/howtoplay" + i + ".jpg")
    new Image(ipStream)
  }

  // Factory function for creating page content
  val createHowToPage = (index: Int) => new VBox() {
    val img = new ImageView(images(index))
    img.fitHeight = 300.0
    img.fitWidth = 400.0
    children = List(img, new Label("Xiangqi " + (index + 1)))
    alignment = Pos.Center
  }

  // Pagination with 8 pages and index starts at zero
  val pagination = new Pagination(8, 0) {
    pageFactory = createHowToPage
    styleClass += Pagination.StyleClassBullet
  }

  paginationLayout.children = pagination
}
