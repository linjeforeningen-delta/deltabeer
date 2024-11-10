package dev.stonegarden.deltahouse.item

import dev.stonegarden.deltahouse.user.UserDAO
import jakarta.persistence.*
import java.io.Serializable
import java.time.ZonedDateTime

@Entity(name = "BorrowedItems")
data class BorrowedItemDAO(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "borrowed_item_id_generator")
    val id: Long = -1,
    @ManyToOne
    val item: ItemDAO,
    @Column(length = 127, nullable = true)
    val itemComment: String?,
    @ManyToOne
    val borrower: UserDAO,
    val borrowedDate: ZonedDateTime = ZonedDateTime.now(),
    val returnByDate: ZonedDateTime,
    val returnedDate: ZonedDateTime? = null,
) : Serializable