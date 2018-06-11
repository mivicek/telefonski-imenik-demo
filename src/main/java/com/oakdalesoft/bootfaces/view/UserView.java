package com.oakdalesoft.bootfaces.view;

import com.oakdalesoft.bootfaces.domain.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Alex on 07/03/2015.
 */

@ManagedBean(name = "user", eager = true)
@RequestScoped
public class UserView extends User {

    public String kwd;

	public UserView() { }
}
