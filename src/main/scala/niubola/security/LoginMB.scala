package niubola.security

import com.github.adminfaces.template.session.AdminSession
import niubola.framework.faces.Page
import org.apache.shiro.authc.{AuthenticationToken, IncorrectCredentialsException, UnknownAccountException, UsernamePasswordToken}
import org.omnifaces.util.{Faces, Messages}

import javax.enterprise.context.SessionScoped
import javax.inject.{Inject, Named}
import scala.beans.BeanProperty
import org.apache.shiro.SecurityUtils
import org.ocpsoft.rewrite.annotation.{Join, RequestAction}
import org.ocpsoft.rewrite.el.ELBeanName
import org.ocpsoft.rewrite.faces.annotation.Deferred

import javax.servlet.annotation.WebServlet
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

@SessionScoped
@ELBeanName("loginPage")
@Named("loginPage")
@Join(path = "/login",to = "/security/login.xhtml")
class LoginPage extends Page {

  @BeanProperty
  var username:String = _
  @BeanProperty
  var password:String = _

  @Deferred
  @RequestAction
  override def onload(): Unit = {
    super.afterRestoreView
  }

  @Inject
  private var adminSession: AdminSession = _

  def login(): Unit = {

    try {
      val token: AuthenticationToken = new UsernamePasswordToken(username, password);
      val currentUser = SecurityUtils.getSubject
      currentUser.login(token)
      adminSession.setIsLoggedIn(true)
      Messages.addWarn(null, "Logged in sucessfully as <b>" + username + "</b>")
      Faces.redirect("/admin")
    }catch{
      case uae: UnknownAccountException => {
        Messages.addError(null, "用户名不存在")
      }
      case ice: IncorrectCredentialsException => {
        Messages.addError(null, "用户名或密码错误")
      }
    }

  }

}


@WebServlet(urlPatterns = Array("/logout"))
class LogoutServlet extends HttpServlet{

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {

    val currentUser = SecurityUtils.getSubject
    currentUser.logout()
    resp.sendRedirect("/login")

  }

}

/**
@SessionScoped
@Specializes
class LoginMB extends AdminSession{



}
*/