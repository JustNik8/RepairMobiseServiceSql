package com.justnik.generate

import com.justnik.DbWorker
import com.justnik.utils.*
import kotlin.random.Random

private const val INSERT_QUERY = "INSERT INTO clients(" +
        "client_name, client_surname, client_patronymic, client_phone_number) " +
        "VALUES (?, ?, ?, ?)"
private const val SELECT_QUERY = "SELECT * FROM clients"
private const val SIZE = 100

fun generateClients() {
    val connection = DbWorker.connection ?: return

    val selectStatement = connection.createStatement()
    val resultSet = selectStatement.executeQuery(SELECT_QUERY)
    if (resultSet.next()) return

    for (i in 0 until SIZE) {
        val fio = if (Random.nextBoolean()) {
            girlsFio
        } else {
            manFio
        }
        val name = fio[fio.indices.random()].split(" ")[0]
        val surname = fio[fio.indices.random()].split(" ")[1]
        val patronymic = fio[fio.indices.random()].split(" ")[2]
        val phoneNum = phoneNums[phoneNums.indices.random()]

        val statement = connection.prepareStatement(INSERT_QUERY)
        statement.setString(1, name)
        statement.setString(2, surname)
        statement.setString(3, patronymic)
        statement.setString(4, phoneNum)

        statement.execute()
    }
}

