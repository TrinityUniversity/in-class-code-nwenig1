import org.scalatestplus.play._
import models._

class TaskListInMemoryModelSpec extends PlaySpec {
    "TaskListInMemoryModel" must {
        "do valid login for default user" in {
            TaskListInMemoryModel.validateUser("Mark", "pass") mustBe (true) 
        }

        "reject login with wrong password" in {
            TaskListInMemoryModel.validateUser("Mark", "wrong") mustBe(false)
        }
        "reject login with wrong username" in {
            TaskListInMemoryModel.validateUser("wrong", "pass") mustBe(false)
        }
    }
}
