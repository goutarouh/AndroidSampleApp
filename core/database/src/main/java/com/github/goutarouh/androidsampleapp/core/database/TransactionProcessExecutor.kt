package com.github.goutarouh.androidsampleapp.core.database

import androidx.room.withTransaction

class TransactionProcessExecutor(
    private val appDatabase: AppDatabase
) {
    suspend fun doTransactionProcess(action: suspend () -> Unit) {
        appDatabase.withTransaction {
            action()
        }
    }
}
