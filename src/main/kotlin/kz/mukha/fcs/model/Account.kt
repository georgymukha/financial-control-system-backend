package kz.mukha.fcs.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "accounts")
data class Account(
    @Id
    @GeneratedValue
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    val type: AccountType,

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    val wallet: Wallet,

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    val owner: User,

    @Column(nullable = false)
    val balance: BigDecimal = BigDecimal.ZERO
)

enum class AccountType {
    CASH, DEBIT_CARD, CREDIT_CARD, SAVINGS, OTHER
}
