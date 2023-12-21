package com.example.myapplication.PersonApi.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.PersonApi.model.ChatData

@Dao
interface ChatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: ChatData)

    @Query("SELECT * FROM chat_table")
    fun getAll(): LiveData<MutableList<ChatData>>

    @Query("SELECT * FROM chat_table WHERE groupname = :groupName")
    fun getMessagesByGroupName(groupName: String): LiveData<MutableList<ChatData>>
}
