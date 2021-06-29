package com.keimos.data

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
data class Patient (
    @Id
    val id: ObjectId = ObjectId.get(),
    val name: String,
    val description: String,
    val createDate: LocalDateTime = LocalDateTime.now(),
    val modifiedDate: LocalDateTime = LocalDateTime.now()
)
