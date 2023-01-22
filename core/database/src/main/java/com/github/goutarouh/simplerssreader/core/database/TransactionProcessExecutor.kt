package com.github.goutarouh.simplerssreader.core.database

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
