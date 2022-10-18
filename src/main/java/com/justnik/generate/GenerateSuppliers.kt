package com.justnik.generate

import com.justnik.DbWorker
import com.justnik.utils.phoneNums

private const val INSERT_QUERY = "INSERT INTO suppliers(supplier_phone) VALUES (?)"
private const val SELECT_QUERY = "SELECT * FROM suppliers"
private const val SIZE = 100

fun generateSuppliers() {
    val connection = DbWorker.connection ?: return

    val selectStatement = connection.createStatement()
    val resultSet = selectStatement.executeQuery(SELECT_QUERY)
    if (resultSet.next()) return


    for (i in 0 until SIZE) {
        val phoneNum = phoneNums[(phoneNums.indices).random()]
        connection.prepareStatement(INSERT_QUERY)?.let { statement ->
            statement.setString(1, phoneNum)

            statement.execute()
        }
    }

}