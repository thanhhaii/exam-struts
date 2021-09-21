package com.demo.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.mindrot.jbcrypt.BCrypt;

import com.demo.entity.Accountstruts;
import com.demo.models.AccountModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

@Namespace("/account")
public class AccountAction extends ActionSupport {
	private Accountstruts account;
	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	@VisitorFieldValidator
	public Accountstruts getAccount() {
		return account;
	}

	public void setAccount(Accountstruts account) {
		this.account = account;
	}

	@Action(value = "index", results = { @Result(name = SUCCESS, location = "/WEB-INF/views/account/index.jsp") })
	public String index() {
		this.account = new Accountstruts();
		return SUCCESS;
	}

	@Action(value = "login", results = { @Result(name = SUCCESS, type = "redirectAction", params = { "namespace",
			"/ticket", "actionName", "index" }), @Result(name = ERROR, location = "/WEB-INF/views/account/index.jsp")
		, @Result(name = INPUT, location = "/WEB-INF/views/account/index.jsp")
	})
	public String login() {
		AccountModel accountModel = new AccountModel();
		boolean status = accountModel.login(account.getUsername(), account.getPassword());
		if (status == false) {
			this.error = "Username or password incorrect";
			return ERROR;
		}
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put("username", account.getUsername());		
		return SUCCESS;
	}

	@Action(value = "register", results = { @Result(name = SUCCESS, location = "/WEB-INF/views/account/register.jsp") })
	public String register() {
		this.account = new Accountstruts();
		return SUCCESS;
	}

	@Action(value = "saveAccount", results = { @Result(name = SUCCESS, type = "redirectAction", params = { "namespace",
			"/account", "actionName", "index" }),
			@Result(name = ERROR, location = "/WEB-INF/views/account/register.jsp") })
	public String saveAccount() {
		AccountModel accountModel = new AccountModel();
		account.setPassword(BCrypt.hashpw(account.getPassword(), BCrypt.gensalt()));
		if (accountModel.create(account) == null) {
			this.error = "Username is exits";
			return ERROR;
		}
		return SUCCESS;
	}
	
}
