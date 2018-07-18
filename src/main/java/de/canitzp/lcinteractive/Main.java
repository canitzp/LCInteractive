package de.canitzp.lcinteractive;

import j2html.TagCreator;
import net.chris54721.openmcauthenticator.OpenMCAuthenticator;
import net.chris54721.openmcauthenticator.exceptions.AuthenticationUnavailableException;
import net.chris54721.openmcauthenticator.exceptions.RequestException;
import net.chris54721.openmcauthenticator.responses.AuthenticationResponse;
import net.chris54721.openmcauthenticator.responses.RefreshResponse;

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
        if(req.getParameterMap().containsKey("email") && req.getParameterMap().containsKey("password")){
            String htmlString;
            try {
                AuthenticationResponse ar = OpenMCAuthenticator.authenticate(req.getParameter("email"), req.getParameter("password"));
                htmlString = ar.getSelectedProfile().getUUID().toString();
            } catch (RequestException | AuthenticationUnavailableException e) {
                htmlString = e.getLocalizedMessage();
                e.printStackTrace();
            }
            resp.getWriter().append(
                    html(
                            head(
                                    title("Limbo Con Registration")
                            ),
                            body(
                                    htmlString
                            )
                    ).renderFormatted()
            );
        } else {
            resp.getWriter().append(
                    html(
                            head(
                                    title("Limbo Con Registration")
                            ),
                            body(
                                    div(
                                            div(
                                                    form(
                                                            label("E-Mail:"),
                                                            br(),
                                                            input().withType("email").withId("email").withName("email").attr("size", 50),
                                                            br(),
                                                            label("Password:"),
                                                            br(),
                                                            input().withType("password").withId("password").withName("password").attr("size", 50),
                                                            br(),
                                                            input().withType("submit")
                                                    ).withMethod("post")
                                            ).withStyle("border: 1px solid black; margin: auto")
                                    ).attr("align", "center")
                            )
                    ).renderFormatted()
            );
        }
    }
}
