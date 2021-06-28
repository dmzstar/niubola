package niubola.admin.faces

import niubola.framework.faces.Page
import org.ocpsoft.rewrite.annotation.Join

import javax.faces.view.ViewScoped
import javax.inject.Named


@ViewScoped
@Named("admin_page")
@Join(path="/admin", to="/admin/users/index")
class AdminPage extends Page{



}
