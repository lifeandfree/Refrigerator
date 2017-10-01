package ru.innopolis.refrigerator.core.model;

import java.util.Date;

public class Session {

	private long id;
	private String session_id;
	private User user;
	private String session_user_agent;
	private Date session_finish_time;
	private Date session_start_time;
	private boolean remember = false;




}
