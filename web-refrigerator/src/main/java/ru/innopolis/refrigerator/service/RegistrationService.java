package ru.innopolis.refrigerator.service;

import ru.innopolis.refrigerator.core.model.enumcls.Role;
import ru.innopolis.refrigerator.service.exception.RegistrationServiceImplException;

public interface RegistrationService {
	Boolean regUser(String login, String password, Role role, String email);

	boolean checkReg(String login, String email) throws RegistrationServiceImplException;

	boolean checkPassword(String password) throws RegistrationServiceImplException;
}

