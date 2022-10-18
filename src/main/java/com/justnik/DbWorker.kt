package com.justnik

import java.sql.Connection
import java.sql.DriverManager

object DbWorker {
    private const val URL = "jdbc:postgresql://localhost:5432/repair_mobile_service"
    private const val NAME = "postgres"
    private const val PASSWORD = "admin"

    val connection: Connection? by lazy {
        DriverManager.getConnection(URL, NAME, PASSWORD)
    }
}