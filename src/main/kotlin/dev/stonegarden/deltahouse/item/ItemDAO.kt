package dev.stonegarden.deltahouse.item

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.io.Serializable
import java.time.ZonedDateTime

@Entity(name = "Items")
data class ItemDAO(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_generator")
    val id: Long = 0,
    @Column(length = 31, nullable = false)
    val name: String,
    @Column(length = 127)
    val description: String?,
    val createdBy: String,
    @CreationTimestamp
    val createdDate: ZonedDateTime = ZonedDateTime.now(),
    val changedBy: String? = null,
    @UpdateTimestamp
    val changedDate: ZonedDateTime? = null,
) : Serializable