package ru.innopolis.refrigerator;

import ru.innopolis.refrigerator.core.db.jdbc.connection.ConnectionFactoryPostgreSQL;

public class Main {
	public static void main(String[] args) {


		ConnectionFactoryPostgreSQL.getInstance().getConnection("jdbc:postgresql://localhost:5432/innopolistest", "innopolistest", "123");

	}
}