package com.justnik.generate

import com.justnik.DbWorker
import com.justnik.utils.phoneNums
import com.justnik.utils.streets

private const val INSERT_QUERY = "INSERT INTO warehouses(legal_address, physical_address, director_phone) VALUES (?,?,?)"
private const val SELECT_QUERY = "SELECT * FROM warehouses"
private const val SIZE = 100

fun generateWarehouses() {
    val connection = DbWorker.connection ?: return

    val selectStatement = connection.createStatement()
    val resultSet = selectStatement.executeQuery(SELECT_QUERY)
    if (resultSet.next()) return


    for (i in 0 until SIZE) {
        val legalStreet = streets[(streets.indices).random()]
        val legalNum = (1..100).random()

        val physicalStreet = streets[(streets.indices).random()]
        val physicalNum = (1..100).random()

        val phoneNum = phoneNums[(phoneNums.indices).random()]
        connection.prepareStatement(INSERT_QUERY)?.let { statement ->
            statement.setString(1, "$legalStreet $legalNum")
            statement.setString(2, "$physicalStreet $physicalNum")
            statement.setString(3, phoneNum)

            statement.execute()
        }
    }

}