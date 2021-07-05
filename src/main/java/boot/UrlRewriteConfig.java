package boot;

import org.ocpsoft.logging.Logger;
import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.*;
import org.ocpsoft.rewrite.el.El;
import org.ocpsoft.rewrite.faces.config.PhaseBinding;
import org.ocpsoft.rewrite.faces.config.PhaseOperation;
import org.ocpsoft.rewrite.servlet.config.Forward;
import org.ocpsoft.rewrite.servlet.config.Redirect;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Resource;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

import javax.faces.event.PhaseId;
import javax.servlet.ServletContext;

@RewriteConfiguration
public class UrlRewriteConfig extends HttpConfigurationProvider {

    @Override
    public int priority() {
        return 0;
    }

    private ConfigurationBuilder config = null;
    private String defaultRestPage = "list.xhtml";
    private String pageBeanPackage = "niubola.admin.faces";

    @Override
    public Configuration getConfiguration(final ServletContext context) {

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
                + Resource.exists("/admin/users/create.xhtml"));

        config = ConfigurationBuilder.begin();

        /**
        restMapping("/admin/users/{path}");
        restMapping("/admin/articles/{path}");
        */
        restPages("/admin/users/:{path}");
        restPages("/admin/:{path}");
        //restMapping("/admin/:{path}");

        return config;


    }


    private void restPages(String namespace,String url,String defaultPage){

        listMapping(namespace);
        showMapping(namespace);
        editMapping(namespace);

        /**
        //add crud rule etc /admin/users/edit/1
        config.addRule(
                Join.path("/admin/users/edit/{id}").to("/admin/users/edit.xhtml")
        ).perform(
                PhaseOperation.enqueue(
                        Invoke.binding(El.retrievalMethod("admin_users_edit.onload"))
                ).after(PhaseId.RESTORE_VIEW)
        );
        */

        // /admin/:{path} will mapping to /admin/index.[xhtml|jsf|faces],or setting the default to such as ‘list.jsf’
        /**
        var namespaceUrl = namespace;
        config.addRule()
                .when(Direction.isInbound().and(Path.matches(namespaceUrl))
                        .and(Resource.exists(namespaceUrl + "/" + defaultPage)))
                    .perform(Log.message(Logger.Level.INFO, "Client requested path: " + namespace + "/" + defaultPage)
                            .and(Forward.to(namespaceUrl + "/" + defaultPage)));
        */

       config.addRule()
                .when(Direction.isInbound().and(Path.matches(url))
                        .and(Resource.exists(url + ".xhtml")))
                .perform(Log.message(Logger.Level.INFO, "Client requested path: {path}")
                        .and(Forward.to(url + ".jsf")))
                .where("path").matches(".*");


    }


    public void listMapping(String resource){

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> listMapping " + resource);
        config.addRule(
                Join.path(resource).to(resource + "/index.xhtml")
        ).perform(
                PhaseOperation.enqueue(
                        Invoke.binding(El.retrievalMethod("admin_users_indexPage.onload"))
                ).after(PhaseId.RESTORE_VIEW)
        );

        /**
        config.addRule()
                .when(Direction.isInbound().and(Path.matches(resource))
                        .and(Resource.exists(resource + "/" + defaultRestPage)))
                .perform(Log.message(Logger.Level.INFO, "Client requested path: " + resource + "/" + defaultRestPage)
                        .and(Forward.to(resource + "/" + defaultRestPage)));
        */

    }

    public void showMapping(String resource) {

        try {

            Class pageClass = Class.forName("niubola.admin.faces.users" + ".ShowPage");

            //Checking


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //add crud rule etc /admin/users/edit/1
        config.addRule(
                Join.path(resource + "/{id}").to(resource + "/show.xhtml")
        )
                .perform(
                PhaseOperation.enqueue(
                        Invoke.binding(El.retrievalMethod("admin_users_showPage.onload"))
                ).after(PhaseId.RESTORE_VIEW)

        ).where("id").bindsTo(PhaseBinding.to(El.property("admin_users_showPage.id")).after(PhaseId.RESTORE_VIEW));

    }

    public void editMapping(String resource){
        //add crud rule etc /admin/users/edit/1
        config.addRule(
                Join.path(resource + "/edit/{id}").to(resource + "/edit.xhtml")
        ).perform(
                PhaseOperation.enqueue(
                        Invoke.binding(El.retrievalMethod("admin_users_edit.afterRestoreView"))
                ).after(PhaseId.RESTORE_VIEW),
                PhaseOperation.enqueue(
                        Invoke.binding(El.retrievalMethod("admin_users_edit.onload"))
                ).after(PhaseId.RESTORE_VIEW)
        ).where("id").bindsTo(PhaseBinding.to(El.property("admin_users_edit.id")).after(PhaseId.RESTORE_VIEW));
    }

    private void restPages(String url){

        var rurl = url.replaceFirst("/:","/");
        // /admin/:{path} will mapping to /admin/index.[xhtml|jsf|faces],or setting the default to such as ‘list.jsf’
        var namespaceUrl = "";
        if(url.lastIndexOf(":") > 0){
            namespaceUrl = url.substring(0,url.lastIndexOf(":") - 1);
           restPages(namespaceUrl,rurl,defaultRestPage);
        }


    }

    private void restMapping(String url){

                /**
                config.addRule()
                .when(Direction.isInbound().and(Path.matches(url))
                        .and(Resource.exists(url + ".xhtml")))
                .perform(Log.message(Logger.Level.INFO, "Client requested path: {path}")
                        .and(Forward.to(url + ".jsf")))
                .where("path").matches(".*");
                 */

        var subPaths = url.split("/");
        var rurl = url;
        var namespace = "";
        var namespaceUrl = "/admin/users";
        if(url.lastIndexOf(":") > 0){
            //rurl = url.substring(0,url.lastIndexOf(":") - 1);
            rurl = url.replaceFirst("/:","/");

        }


        config.addRule()
                .when(Direction.isInbound().and(Path.matches(namespaceUrl))
                        .and(Resource.exists(namespaceUrl + "/index.xhtml")))
                .perform(Log.message(Logger.Level.INFO, "Client requested path: }")
                        .and(Forward.to(namespaceUrl + "/index.jsf")));
                //.where("path").matches(".*");


        var co = config.addRule()
                .when(Direction.isInbound().and(Path.matches(url))
                        .and(Resource.exists(url + ".xhtml")))
                .perform(Log.message(Logger.Level.INFO, "Client requested path: {path}")
                        .and(Forward.to(url + ".jsf")))
                .where("path").matches(".*");



    }


    private void restMapping(String dir,String url){
        restMapping(dir + url);
    }

}
