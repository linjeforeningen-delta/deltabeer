package dev.stonegarden.deltahouse.wallet

import dev.stonegarden.deltahouse.user.UserDAO
import dev.stonegarden.deltahouse.user.UserPropertyType

data class UserWallet(
    val userDAO: UserDAO,
    val walletDAO: WalletDAO,
) {
    init {
        if (walletDAO.user.id != userDAO.id) {
            throw dev.stonegarden.deltahouse.exceptions.WalletUserMismatchException()
        }
    }

    fun creditRating() =
        userDAO.userProperties.find { it.propertyType == UserPropertyType.CREDIT }?.propertyValue?.toByte() ?: 0

    fun cashBalance() = walletDAO.cashBalance
    fun latestTransaction() = walletDAO.latestTransaction?.let { Transaction(it) }
    fun totalSpent() = walletDAO.totalSpent
    fun cardId() = userDAO.cardId
    fun name() = userDAO.firstName + " " + userDAO.lastName
}