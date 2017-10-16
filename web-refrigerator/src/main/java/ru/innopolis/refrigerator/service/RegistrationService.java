package ru.innopolis.refrigerator.service;

import ru.innopolis.refrigerator.core.model.enumcls.Role;

public interface RegistrationService {
	Boolean regUser(String login, String password, Role role, String email);
}

