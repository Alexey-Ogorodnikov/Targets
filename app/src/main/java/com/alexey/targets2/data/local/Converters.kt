package com.alexey.targets2.data.local

import androidx.room.TypeConverter
import com.alexey.targets2.data.model.Priority

class Converters {
    
    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }
    
    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }
} 