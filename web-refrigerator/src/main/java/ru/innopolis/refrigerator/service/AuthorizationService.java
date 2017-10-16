package ru.innopolis.refrigerator.service;

public interface AuthorizationService {
	Boolean auth(String login, String password);
}

