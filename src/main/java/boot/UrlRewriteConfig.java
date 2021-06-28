package boot;

import org.ocpsoft.logging.Logger;
import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.config.Log;
import org.ocpsoft.rewrite.servlet.config.Forward;
import org.ocpsoft.rewrite.servlet.config.Redirect;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Resource;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

import javax.servlet.ServletContext;

@RewriteConfiguration
public class UrlRewriteConfig extends HttpConfigurationProvider {

    @Override
    public int priority() {
        return 0;
    }

    private ConfigurationBuilder config = null;

    @Override
    public Configuration getConfiguration(final ServletContext context) {

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
                + Resource.exists("/admin/users/create.xhtml"));

        /**
         return ConfigurationBuilder.begin()
         .addRule(Join.path("/my/create/{path}")
         .to("/admin/users/create.jsf"))
         .when(Resource.exists("/admin/users/create.xhtml"))
         .otherwise(Redirect.temporary("http://www.baidu.com"))
         .perform(Log.message(Logger.Level.INFO, "Client requested path: {path}"));
         */

        /**
         return ConfigurationBuilder.begin()
         .addRule(Join.path("/my/create/{path}")

         .to("/admin/users/{path}.jsf"))
         .perform(Log.message(Logger.Level.INFO, "Client requested path: {path}"));
         */

        config = ConfigurationBuilder.begin();

        /**
        restMapping("/admin/users/{path}");
        restMapping("/admin/articles/{path}");
        */
        restMapping("/admin/{path}");

        if(true){
            return config;
        }

        return ConfigurationBuilder.begin()
                .addRule()
                .when(Direction.isInbound().and(Path.matches("/my/create/{path}"))
                .and(Resource.exists("/admin/users/create.xhtml")))
                .perform(Log.message(Logger.Level.INFO, "Client requested path: {path}")
                        .and(Forward.to("/admin/users/create.jsf")))
                .where("path").matches("new")
                .addRule()
                .when(Direction.isInbound().and(Path.matches("/my/create/{path}"))
                .and(Resource.exists("/admin/users/{path}.xhtml")))
                .perform(Log.message(Logger.Level.INFO, "Client requested path: {path}")
                        .and(Forward.to("/admin/users/{path}.jsf")))
                .where("path").matches(".*");

        /**
         return ConfigurationBuilder.begin().addRule().
         perform(Log.message(Logger.Level.INFO, "Rewrite actived."));
         */
    }


    private void restMapping(String url){
                 config.addRule()
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
