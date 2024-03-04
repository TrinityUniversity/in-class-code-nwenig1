import org.scalatestplus.play.PlaySpec

import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import org.scalatestplus.play.OneBrowserPerSuite
import org.scalatestplus.play.HtmlUnitFactory


class TaskList1Spec extends PlaySpec with GuiceOneServerPerSuite with OneBrowserPerSuite with HtmlUnitFactory{
    "Task List 1" must {
        "login and access functions" in {
            go to s"http://localhost:$port/login1"
            pageTitle mustBe "Login"
            find(cssSelector("h2")).isEmpty mustBe false
            find(cssSelector("h2")).foreach( e=> e.text mustBe "Login")
            click on "username-login" 
            textField("username-login").value="Mark"
            click on "password-login"
            pwdField(id("password-login")).value="pass"
            submit()
            eventually{
                pageTitle mustBe "TaskList"
                find(cssSelector("h2")).isEmpty mustBe false
                find(cssSelector("h2")).foreach( e=> e.text mustBe "Task List")
                findAll(cssSelector("li")).toList.map(_.text) mustBe List("Make Videos", "climb", "sleep")
            }
        }
    }
}