import org.scalatestplus.play._
import models._
class day2StuffTestSpec extends PlaySpec{
"day2StuffMemoryModel" must {
    "do valid login for noah" in {
        day2StuffMemoryModel.nameExists("Noah") mustBe (true)
    }
}

}