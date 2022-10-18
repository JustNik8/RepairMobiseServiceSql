package com.justnik.generate

import com.justnik.DbWorker
import com.justnik.utils.devices

private const val INSERT_QUERY = "INSERT INTO mobile_devices(manufacturer, model) VALUES (?,?)"
private const val SELECT_QUERY = "SELECT * FROM mobile_devices"
private const val SIZE = 100

fun generateMobileDevices() {
    val connection = DbWorker.connection ?: return

    val selectStatement = connection.createStatement()
    val resultSet = selectStatement.executeQuery(SELECT_QUERY)
    if (resultSet.next()) return


    for (i in 0 until SIZE) {
        val device = devices[devices.indices.random()]

        val manufacturer = device.first
        val model = device.second[device.second.indices.random()]

        val statement = connection.prepareStatement(INSERT_QUERY)
        statement.setString(1, manufacturer)
        statement.setString(2, model)

        statement.execute()
    }

}