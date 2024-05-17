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
@Table(name = "transaction_items")
data class TransactionItem(
    @Id
    @GeneratedValue
    val id: UUID = UUID.randomUUID(),

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    val transaction: Transaction,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val quantity: BigDecimal,

    @Column(nullable = false)
    val price: BigDecimal
)