package com.justnik.generate

import com.justnik.DbWorker
import com.justnik.utils.*
import java.sql.Connection
import kotlin.random.Random

private const val INSERT_QUERY = "INSERT INTO employees(" +
        "employee_name, employee_surname, employee_patronymic, employee_phone_number, employee_birthdate, position_id) " +
        "VALUES (?, ?, ?, ?, ?, ?)"
private const val SELECT_QUERY = "SELECT * FROM employees"
private const val SELECT_POSITIONS = "SELECT * FROM positions"

private const val masterId = 1
private const val administratorId = 2
private const val directorId = 3

fun generateEmployees() {
    val connection = DbWorker.connection ?: return

    val selectStatement = connection.createStatement()
    val resultSet = selectStatement.executeQuery(SELECT_QUERY)
    if (resultSet.next()) return

    generateWithPosition(connection, directorId)

    for (i in 0 until 3) {
        generateWithPosition(connection, administratorId)
    }

    for (i in 0 until 15) {
        generateWithPosition(connection, masterId)
    }
}

private fun generateWithPosition(connection: Connection, positionId: Int) {
    val fio = if (Random.nextBoolean()) {
        girlsFio
    } else {
        manFio
    }
    val name = fio[fio.indices.random()].split(" ")[0]
    val surname = fio[fio.indices.random()].split(" ")[1]
    val patronymic = fio[fio.indices.random()].split(" ")[2]
    val phoneNum = phoneNums[phoneNums.indices.random()]
    val date = java.sql.Date(getRandomDate().time * 1000)

    val statement = connection.prepareStatement(INSERT_QUERY)
    statement.setString(1, name)
    statement.setString(2, surname)
    statement.setString(3, patronymic)
    statement.setString(4, phoneNum)
    statement.setDate(5, date)
    statement.setInt(6, positionId)

    statement.execute()
}
