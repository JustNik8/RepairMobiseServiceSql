package com.justnik.generate

import com.justnik.DbWorker
import com.justnik.utils.devices

private const val INSERT_QUERY = "INSERT INTO requests" +
        "(admisson_date, work_status, amount, problem_description, employee_id, client_id, mobile_device_id) " +
        "VALUES (?,?,?,?,?,?,?)"
private const val SELECT_QUERY = "SELECT * FROM requests"
private const val SIZE = 100

fun generateRequests() {
    val connection = DbWorker.connection ?: return

    val selectStatement = connection.createStatement()
    val resultSet = selectStatement.executeQuery(SELECT_QUERY)
    if (resultSet.next()) return


    for (i in 0 until SIZE) {
        val date = java.sql.Date((1514754000L..1659301200L).random())
        val workStatus = if (date.time > 1656622800) {
            "В работе"
        } else {
            "Выполнен"
        }
        val amount = (1000..10000).random()

    }

}