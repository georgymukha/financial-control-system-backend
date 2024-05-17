package kz.mukha.fcs.service

import kz.mukha.fcs.model.Account
import kz.mukha.fcs.model.Transaction
import kz.mukha.fcs.model.TransactionType
import kz.mukha.fcs.model.User
import kz.mukha.fcs.model.Wallet
import kz.mukha.fcs.repository.TransactionRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository,
) {
    fun createTransaction(
        amount: BigDecimal,
        type: TransactionType,
        category: String,
        wallet: Wallet,
        account: Account,
        user: User,
        description: String?
    ): Transaction {
        val transaction = Transaction(
            amount = amount,
            type = type,
            category = category,
            wallet = wallet,
            account = account,
            user = user,
            description = description,
            date = LocalDateTime.now(),
        )
        return transactionRepository.save(transaction)
    }

    fun getTransactionById(id: UUID): Transaction? {
        return transactionRepository.findById(id).orElse(null)
    }

    fun deleteTransaction(id: UUID) {
        transactionRepository.deleteById(id)
    }
}