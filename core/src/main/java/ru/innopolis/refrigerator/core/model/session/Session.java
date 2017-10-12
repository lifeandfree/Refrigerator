package ru.innopolis.refrigerator.core.model.session;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import ru.innopolis.refrigerator.core.model.user.User;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Session", propOrder = {

})
@XmlRootElement(name = "Session")
@Entity
@Table(name = "\"Session\"")
public class Session implements Serializable {

	public Session() {
	}

	public Session(String sessionId, User user, String session_user_agent, Timestamp session_finish_time, Timestamp session_start_time, boolean remember) {
		this.sessionId = sessionId;
		this.user = user;
		this.session_user_agent = session_user_agent;
		this.session_finish_time = session_finish_time;
		this.session_start_time = session_start_time;
		this.remember = remember;
	}

	@XmlElement(required = true)
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@XmlElement(required = true)
	@Column(name = "sessionId", nullable = false, length = 100)
	private String sessionId;

	@XmlElement(required = true)
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@XmlElement(required = true)
	@Column(name = "session_user_agent", nullable = true, length = 100)
	private String session_user_agent;

	@XmlElement(required = true)
	@Column(name = "session_finish_time", unique = true, nullable = false)
	@Type(type = "timestamp")
	private Timestamp session_finish_time;

	@XmlElement(required = true)
	@Column(name = "session_start_time", unique = false, nullable = false)
	@Type(type = "timestamp")
	private Timestamp session_start_time;

	@XmlElement(required = true)
	@Column(name = "remember", unique = false)
	@Type(type = "numeric_boolean")
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

	public void setSession_finish_time(Timestamp session_finish_time) {
		this.session_finish_time = session_finish_time;
	}

	public Date getSession_start_time() {
		return session_start_time;
	}

	public void setSession_start_time(Timestamp session_start_time) {
		this.session_start_time = session_start_time;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	@Override
	public String toString() {
		return "Session{" + "sessionId='" + sessionId + '\'' + ", user=" + user + ", session_user_agent='" + session_user_agent + '\'' + ", session_finish_time=" + session_finish_time + ", session_start_time=" + session_start_time + ", remember=" + remember + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Session session = (Session) o;

		if (sessionId != null ? !sessionId.equals(session.sessionId) : session.sessionId != null)
			return false;
		if (session_user_agent != null ? !session_user_agent.equals(session.session_user_agent) : session.session_user_agent != null)
			return false;
		return session_start_time != null ? session_start_time.equals(session.session_start_time) : session.session_start_time == null;
	}

	@Override
	public int hashCode() {
		int result = sessionId != null ? sessionId.hashCode() : 0;
		result = 31 * result + (session_user_agent != null ? session_user_agent.hashCode() : 0);
		result = 31 * result + (session_start_time != null ? session_start_time.hashCode() : 0);
		return result;
	}
}
