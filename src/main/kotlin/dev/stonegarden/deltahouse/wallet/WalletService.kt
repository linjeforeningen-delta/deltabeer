package dev.stonegarden.deltahouse.wallet

import dev.stonegarden.deltahouse.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WalletService(
    @Autowired val userService: UserService,
    @Autowired val walletRepository: WalletRepository,
    @Autowired val transactionRepository: TransactionRepository,
    @Value("\${transaction.deposit.max:1000}") val maxDepositValue: Int = 1000,
    @Value("\${transaction.purchase.max:500}") val maxPurchaseValue: Int = 500,
    @Value("\${transaction.tab.multiplier:100}") val creditMultiplier: Int = 100
) {

    fun getWallet(cardId: Long): UserWallet {
        return getOrCreateUserWalletByCardId(cardId)
    }

    @Transactional
    fun purchase(cardId: Long, price: Short): UserWallet {
        val userWallet = getOrCreateUserWalletByCardId(cardId)

        val creditRating = userWallet.creditRating()
        val cashBalance = userWallet.cashBalance()

        if (price > maxPurchaseValue) {
            throw dev.stonegarden.deltahouse.exceptions.InvalidTransactionException("Purchase value too high. Max price is ${maxPurchaseValue}.")
        }
        if (price <= 0) {
            throw dev.stonegarden.deltahouse.exceptions.InvalidTransactionException("Purchase price must be positive.")
        }
        if (cashBalance + creditRating * creditMultiplier < price) {
            throw dev.stonegarden.deltahouse.exceptions.InvalidTransactionException(
                "Not enough funds to complete purchase. Current balance $cashBalance"
                        + if (creditRating > 0) " with a tab of ${creditRating * creditMultiplier}." else "."
            )
        }
        return performTransaction(userWallet, (-price).toShort())
    }

    @Transactional
    fun deposit(cardId: Long, amount: Short): UserWallet {
        val userWallet = getOrCreateUserWalletByCardId(cardId)

        val cashBalance = userWallet.cashBalance()

        if (amount > maxDepositValue) {
            throw dev.stonegarden.deltahouse.exceptions.InvalidTransactionException("Deposit value too high. Max deposit is ${maxDepositValue}.")
        }
        if (amount <= 0) {
            throw dev.stonegarden.deltahouse.exceptions.InvalidTransactionException("Deposit must be positive.")
        }
        // If this is true it is very like we've run into an integer overflow
        if (cashBalance + amount < Int.MIN_VALUE + amount) {
            throw dev.stonegarden.deltahouse.exceptions.InvalidTransactionException("Depositing more would result in an integer overflow.")
        }
        return performTransaction(userWallet, amount)
    }

    private fun performTransaction(userWallet: UserWallet, change: Short): UserWallet {
        val updatedWalletDAO = walletRepository.save(
            userWallet.walletDAO.copy(
                cashBalance = userWallet.cashBalance() + change,
                totalSpent = userWallet.totalSpent() + change * (change < 0).compareTo(false),
                latestTransaction = transactionRepository.save(TransactionDAO(userWallet.walletDAO, change))
            )
        )
        return UserWallet(userWallet.userDAO, updatedWalletDAO)
    }

    private fun getOrCreateUserWalletByCardId(cardId: Long): UserWallet {
        val userDAO = userService.getUserDAOByCardId(cardId)
        val walletDAO = walletRepository.findByUserId(userDAO.id)
        return if (walletDAO.isPresent) {
            UserWallet(userDAO, walletDAO.get())
        } else {
            UserWallet(
                userDAO, walletRepository.save(
                    WalletDAO(
                        user = userDAO,
                        cashBalance = 0,
                        totalSpent = 0
                    )
                )
            )
        }

    }
}