package com.justnik.generate

import com.justnik.DbWorker

private const val INSERT_QUERY = "INSERT INTO spareparts(price, warehouse_id, supplier_id) VALUES (?,?,?)"
private const val SELECT_QUERY = "SELECT * FROM spareparts"
private const val SELECT_SUPPLIERS = "SELECT * FROM suppliers"
private const val SELECT_WAREHOUSES = "SELECT * FROM warehouses"
private const val SIZE = 100

fun generateSpareParts() {
    val connection = DbWorker.connection ?: return

    val selectStatement = connection.createStatement()
    val resultSet = selectStatement.executeQuery(SELECT_QUERY)
    if (resultSet.next()) return


    for (i in 0 until SIZE) {
        val price = (100..10_000).random()
        val suppliersRes = connection.createStatement().executeQuery(SELECT_SUPPLIERS)
        val warehousesRes = connection.createStatement().executeQuery(SELECT_WAREHOUSES)

        val suppliersIds = mutableSetOf<Int>()
        while (suppliersRes.next()) {
            suppliersIds.add(suppliersRes.getInt("supplier_id"))
        }
        val warehousesIds = mutableSetOf<Int>()
        while (warehousesRes.next()) {
            warehousesIds.add(warehousesRes.getInt("warehouse_id"))
        }

        val supplierId = suppliersIds.random()
        val wareHouseId = warehousesIds.random()

        connection.prepareStatement(INSERT_QUERY)?.let { statement ->

            statement.setInt(1, price)
            statement.setInt(2, wareHouseId)
            statement.setInt(3, supplierId)
            statement.execute()
        }
    }

}