package dev.stonegarden.deltahouse.wallet

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.io.Serializable
import java.time.ZonedDateTime

@Entity(name = "Transactions")
data class TransactionDAO(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_generator")
    val id: Long = -1,
    @ManyToOne(fetch = FetchType.LAZY)
    val wallet: WalletDAO,
    val previousBalance: Int,
    val balanceChange: Short,
    val previousTransactionId: Long? = null,
    val transactionHash: Long?,
    @CreationTimestamp
    val transactionDate: ZonedDateTime = ZonedDateTime.now(),
) : Serializable {
    constructor(wallet: WalletDAO, change: Short) : this(
        id = -1,
        wallet = wallet,
        previousBalance = wallet.cashBalance,
        balanceChange = change,
        previousTransactionId = wallet.latestTransaction?.id,
        transactionHash = 977 * (977 * ((wallet.latestTransaction?.transactionHash
            ?: 0) + 977 * wallet.cashBalance) + (wallet.latestTransaction?.transactionDate.hashCode())),
    )
}