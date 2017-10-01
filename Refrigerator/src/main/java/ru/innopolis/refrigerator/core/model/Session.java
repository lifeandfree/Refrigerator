package ru.innopolis.refrigerator.core.model;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Session", propOrder = {

})
@XmlRootElement(name = "Session")
public class Session {

	public Session() {
	}

	public Session(String sessionId, User user, String session_user_agent, Date session_finish_time, Date session_start_time, boolean remember) {
		this.sessionId = sessionId;
		this.user = user;
		this.session_user_agent = session_user_agent;
		this.session_finish_time = session_finish_time;
		this.session_start_time = session_start_time;
		this.remember = remember;
	}

	private long id;
	@XmlElement(required = true)
	private String sessionId;
	@XmlElement(required = true)
	private User user;
	@XmlElement(required = true)
	private String session_user_agent;
	@XmlElement(required = true)
	private Date session_finish_time;
	@XmlElement(required = true)
	private Date session_start_time;
	@XmlElement(required = true)
	private boolean remember = false;

	public long getId() {
		return id;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSession_user_agent() {
		return session_user_agent;
	}

	public void setSession_user_agent(String session_user_agent) {
		this.session_user_agent = session_user_agent;
	}

	public Date getSession_finish_time() {
		return session_finish_time;
	}

	public void setSession_finish_time(Date session_finish_time) {
		this.session_finish_time = session_finish_time;
	}

	public Date getSession_start_time() {
		return session_start_time;
	}

	public void setSession_start_time(Date session_start_time) {
		this.session_start_time = session_start_time;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}
}
