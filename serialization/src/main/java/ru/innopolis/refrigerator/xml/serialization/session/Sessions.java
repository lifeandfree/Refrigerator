package ru.innopolis.refrigerator.xml.serialization.session;

import ru.innopolis.refrigerator.core.model.session.Session;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sessions", propOrder = {"sessions"})
@XmlRootElement(name = "Sessions")
public class Sessions {

	public Sessions() {
		this.sessions = new ArrayList<>();
	}

	public Sessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	@XmlElement(required = true)
	private List<Session> sessions;

	@Override
	public String toString() {
		return "Sessions{" + "sessions=" + sessions + '}';
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}
}
