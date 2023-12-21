package com.example.myapplication.PersonApi.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.PersonApi.model.ChatData


@Database(entities = [ChatData::class], version = 2)
abstract class ChatDatabase : RoomDatabase() {

    abstract val chatDao: ChatDao
}

private lateinit var dbChatInstance: ChatDatabase

fun getChatDatabase(context: Context): ChatDatabase {
    synchronized(ChatDatabase::class.java) {
        if (!::dbChatInstance.isInitialized) {
            dbChatInstance = Room.databaseBuilder(
                context.applicationContext,
                ChatDatabase::class.java,
                "chat_database"
            ).build()
        }
        return dbChatInstance
    }
}
