package com.paung.views.login;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("login")
public class LoginView extends VerticalLayout {

    LoginView() {
        var form = new LoginForm();
        form.setAction("login");
        add(form);
        setAlignItems(Alignment.CENTER);
    }
}
