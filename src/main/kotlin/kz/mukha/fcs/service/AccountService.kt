package kz.mukha.fcs.service

import kz.mukha.fcs.model.Account
import kz.mukha.fcs.model.AccountType
import kz.mukha.fcs.model.User
import kz.mukha.fcs.model.Wallet
import kz.mukha.fcs.repository.AccountRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AccountService(
    private val accountRepository: AccountRepository,
) {

    fun createAccount(name: String, type: AccountType, wallet: Wallet, owner: User): Account {
        val account = Account(name = name, type = type, wallet = wallet, owner = owner)
        return accountRepository.save(account)
    }

    fun getAccountById(id: UUID): Account? {
        return accountRepository.findById(id).orElse(null)
    }

    fun deleteAccount(id: UUID) {
        accountRepository.deleteById(id)
    }
}