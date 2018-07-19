package de.canitzp.lcinteractive;

import j2html.tags.ContainerTag;
import net.chris54721.openmcauthenticator.OpenMCAuthenticator;
import net.chris54721.openmcauthenticator.exceptions.AuthenticationUnavailableException;
import net.chris54721.openmcauthenticator.exceptions.RequestException;
import net.chris54721.openmcauthenticator.responses.AuthenticationResponse;
import sun.rmi.runtime.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static j2html.TagCreator.*;

/**
 * @author canitzp
 */
public class Main extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doStuff(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameterMap());
        doStuff(req, resp);
    }

    public void doStuff(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        ContainerTag html = html(
                head(
                        title("Limbo Con Registration")
                )
        );
        if(req.getParameterMap().containsKey(LoginPage.INPUT_NAME_EMAIL) && req.getParameterMap().containsKey(LoginPage.INPUT_NAME_PASSWORD)){
            String htmlString;
            try {
                AuthenticationResponse ar = OpenMCAuthenticator.authenticate(req.getParameter(LoginPage.INPUT_NAME_EMAIL), req.getParameter(LoginPage.INPUT_NAME_PASSWORD));
                htmlString = ar.getSelectedProfile().getUUID().toString();
            } catch (RequestException | AuthenticationUnavailableException e) {
                htmlString = e.getLocalizedMessage();
                e.printStackTrace();
            }
            html.with(body(htmlString));
        } else {
            LoginPage.render(html);
        }
        resp.getWriter().append(html.renderFormatted());
    }
}
