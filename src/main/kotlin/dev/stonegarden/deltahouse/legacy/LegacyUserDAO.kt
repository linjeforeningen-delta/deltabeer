package dev.stonegarden.deltahouse.legacy

import jakarta.persistence.Id
import java.io.Serializable

data class LegacyUserDAO(
    @Id
    val cardId: Long,
    val firstName: String,
    val lastName: String,
    val username: String,
    val birthday: String,
    val studprog: String,
    val membership: Int,
    val userlevel: Int,
    val password: String,
    val tab: Int,
    val cash: Int,
    val spent: Int,
    val borrowed: String,
    val comment: String,
    val misc: String,
    val creationDate: Int
) : Serializable
