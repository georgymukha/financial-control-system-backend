package kz.mukha.fcs.service

import kz.mukha.fcs.model.Account
import kz.mukha.fcs.model.Transaction
import kz.mukha.fcs.model.TransactionItem
import kz.mukha.fcs.repository.TransactionItemRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.UUID

@Service
class TransactionItemService(
    private val transactionItemRepository: TransactionItemRepository,
) {
    fun createTransactionItem(
        transaction: Transaction,
        name: String,
        quantity: BigDecimal,
        price: BigDecimal
    ): TransactionItem {
        val transactionItem = TransactionItem(
            transaction = transaction,
            name = name,
            quantity = quantity,
            price = price
        )
        return transactionItemRepository.save(transactionItem)
    }

    fun getTransactionItemById(id: UUID): TransactionItem? {
        return transactionItemRepository.findById(id).orElse(null)
    }

    fun deleteTransactionItem(id: UUID) {
        transactionItemRepository.deleteById(id)
    }
}