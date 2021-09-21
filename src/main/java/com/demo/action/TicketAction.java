package com.demo.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.demo.entity.Categorystruts;
import com.demo.entity.Status;
import com.demo.entity.Ticket;
import com.demo.models.AccountModel;
import com.demo.models.CategoryModel;
import com.demo.models.StatusModel;
import com.demo.models.TicketModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

@Namespace("/ticket")
public class TicketAction extends ActionSupport {
	private List<Ticket> tickets;
	private int accountId;
	private Ticket ticket;
	private Map<Integer, String> status;
	private Map<Integer, String> categories;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<Integer, String> getStatus() {
		return status;
	}

	public void setStatus(Map<Integer, String> status) {
		this.status = status;
	}

	public Map<Integer, String> getCategories() {
		return categories;
	}

	public void setCategories(Map<Integer, String> categories) {
		this.categories = categories;
	}

	@VisitorFieldValidator
	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@Action(value = "index", results = { @Result(name = SUCCESS, location = "/WEB-INF/views/ticket/index.jsp"),
			@Result(name = ERROR, type = "redirectAction", params = { "namespace", "/account", "actionName",
					"index" }) })
	public String index() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String username = (String) session.get("username");
		if (username == null) {
			return ERROR;
		}
		TicketModel ticketModel = new TicketModel();
		AccountModel accountModel = new AccountModel();
		this.tickets = ticketModel.findAll(accountModel.find(username).getId());
		return SUCCESS;
	}

	@Action(value = "create", results = { @Result(name = SUCCESS, location = "/WEB-INF/views/ticket/create.jsp"),
			@Result(name = ERROR, type = "redirectAction", params = { "namespace", "/account", "actionName",
					"index" }) })
	public String create() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String username = (String) session.get("username");
		if (username == null) {
			return ERROR;
		}
		StatusModel statusModel = new StatusModel();
		CategoryModel categoryModel = new CategoryModel();
		this.ticket = new Ticket();
		this.status = statusModel.findAll();
		this.categories = categoryModel.findAll();
		return SUCCESS;
	}

	@Action(value = "saveTicket", results = { @Result(name = SUCCESS, type = "redirectAction", params = { "namespace",
			"/ticket", "actionName", "index" }),
			@Result(name = INPUT, location = "/WEB-INF/views/ticket/create.jsp")
	})
	public String saveTicket() {
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		String username = (String) session.get("username");
		AccountModel accountModel = new AccountModel();
		TicketModel ticketModel = new TicketModel();
		StatusModel statusModel = new StatusModel();
		CategoryModel categoryModel = new CategoryModel();
		Status statusGet = statusModel.find(ticket.getStatus().getId());
		Categorystruts categoryGet = categoryModel.find(ticket.getCategorystruts().getId());
		this.status = statusModel.findAll();
		this.categories = categoryModel.findAll();
		ticket.setAccountstruts(accountModel.find(username));
		ticket.setStatus(statusGet);
		ticket.setCategorystruts(categoryGet);
		ticket.setCreatedAt(new Date());
		ticketModel.create(ticket);
		return SUCCESS;
	}

	@Action(value = "delete", results = { @Result(name = SUCCESS, type = "redirectAction", params = { "namespace",
			"/ticket", "actionName", "index" }) })
	public String delete() {
		TicketModel ticketModel = new TicketModel();
		System.out.println(id);
		ticketModel.delete(ticketModel.find(id));
		return SUCCESS;
	}
}
