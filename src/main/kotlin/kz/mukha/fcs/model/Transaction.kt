package kz.mukha.fcs.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "transactions")
data class Transaction(
    @Id
    @GeneratedValue
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    val amount: BigDecimal,

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    val type: TransactionType,

    @Column(nullable = false)
    val category: String,

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    val wallet: Wallet,

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    val account: Account,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(nullable = false)
    val date: LocalDateTime = LocalDateTime.now(),

    val description: String?
)

enum class TransactionType {
    INCOME, EXPENSE
}
