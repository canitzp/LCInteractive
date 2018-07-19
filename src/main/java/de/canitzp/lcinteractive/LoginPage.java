package de.canitzp.lcinteractive;

import j2html.tags.ContainerTag;

import java.io.PrintWriter;

import static j2html.TagCreator.*;

public class LoginPage {

    static final String INPUT_NAME_EMAIL = "email";
    static final String INPUT_NAME_PASSWORD = "password";

    static void render(ContainerTag html){
        html.with(
                body(
                        div(
                                form(
                                        fieldset(
                                                legend("Minecraft Account").attr("align", "center"),
                                                label("E-Mail:"),
                                                br(),
                                                input().withType("email").withId(INPUT_NAME_EMAIL).withName(INPUT_NAME_EMAIL).attr("size", 50),
                                                br(),
                                                label("Password:"),
                                                br(),
                                                input().withType("password").withId(INPUT_NAME_PASSWORD).withName(INPUT_NAME_PASSWORD).attr("size", 50),
                                                br(),
                                                input().withType("submit")
                                        ).withStyle("width: 100; margin: auto;")
                                ).withMethod("post")
                        ).attr("align", "center")
                )
        );
    }

}
