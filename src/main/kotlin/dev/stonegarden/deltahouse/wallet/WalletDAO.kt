package dev.stonegarden.deltahouse.wallet

import dev.stonegarden.deltahouse.user.UserDAO
import jakarta.persistence.*
import java.io.Serializable

@Entity(name = "Wallets")
data class WalletDAO(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wallet_id_generator")
    val id: Long = -1,
    @OneToOne
    val user: UserDAO,
    // If this were for real money we should use BigDecimal instead of Int
    val cashBalance: Int,
    val totalSpent: Int,
    @OneToOne(cascade = [CascadeType.ALL])
    val latestTransaction: TransactionDAO? = null
) : Serializable
