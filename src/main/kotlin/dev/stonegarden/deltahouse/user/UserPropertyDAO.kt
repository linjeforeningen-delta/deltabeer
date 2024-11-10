package dev.stonegarden.deltahouse.user

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.io.Serializable
import java.time.ZonedDateTime

@Entity(name = "UserProperties")
data class UserPropertyDAO(
    @Enumerated(EnumType.STRING)
    @Column(length = 15, nullable = false)
    val propertyType: UserPropertyType,
    @Column(length = 63, nullable = true)
    val propertyValue: String?,
    @Column(length = 15, nullable = false)
    val createdBy: String,
    @CreationTimestamp
    val createdDate: ZonedDateTime = ZonedDateTime.now(),
    @Column(length = 15, nullable = true)
    val changedBy: String? = null,
    @UpdateTimestamp
    val changedDate: ZonedDateTime? = null
) : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_property_id_generator")
    val id: Long = -1


}