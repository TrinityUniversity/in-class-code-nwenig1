import org.scalatestplus.play.PlaySpec
import controllers.Application
import play.api.test.Helpers
import play.api.test.FakeRequest
import play.api.test.Helpers._

class ControllerSpec extends PlaySpec{
    "Application#index" must{
        "give back expcted page" in {
            val controller = new Application(Helpers.stubControllerComponents())
            val result = controller.index.apply(FakeRequest())
            val bodyText = contentAsString(result) 
            bodyText must include ("Play and Scala.js")
        }
        "give back a product" in {
            val result = controller.product("test", 42).apply(FakeRequest())
            val bodyTest = contentAsString(result)
            bodyText mustBe ("Product type is: test, procudt number is: 42")
        }
    }
}
