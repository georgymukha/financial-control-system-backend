package kz.mukha.fcs.service

import kz.mukha.fcs.model.User
import kz.mukha.fcs.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {

    fun registerUser(username: String, password: String): User {
        val user = User(username = username, password = passwordEncoder.encode(password))
        return userRepository.save(user)
    }

    fun findUserByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }

    fun deleteUser(id: UUID) {

    }
}