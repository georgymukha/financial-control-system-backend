package kz.mukha.fcs.service

import kz.mukha.fcs.model.User
import kz.mukha.fcs.repository.UserRepository
import kz.mukha.fcs.util.LoggerUtil.getLogger
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {

    private val logger = getLogger(this::class.java)

    fun registerUser(username: String, password: String, email: String): User {
        val createdAt = LocalDateTime.now()
        val user = User(username = username, password = passwordEncoder.encode(password), email = email, createdAt = createdAt)
        val createdUser = userRepository.save(user)
        logger.debug("User with username `{}` created: `{}`", username, createdUser)
        return createdUser
    }

    fun findUserByUsername(username: String): User? {
        val user = userRepository.findByUsername(username)
        logger.debug("User with username `{}` found: `{}`", username, user)
        return user
    }

    fun findUserById(id: UUID): User {
        val user = userRepository.findByIdOrNull(id) ?: throw UsernameNotFoundException("User and username with id `$id` not found")
        logger.debug("User with id `{}` found: `{}`", id, user)
        return user
    }

    fun userExistsById(id: UUID): Boolean {
        val exists = userRepository.existsById(id)
        logger.debug("User with id `{}` exists: `{}`", id, exists)
        return exists
    }

    fun userExistsByUsername(username: String): Boolean {
        val exists = userRepository.existsByUsername(username)
        logger.debug("User with username `$username` exists: `$exists`")
        return exists
    }

    fun userExistsByEmail(email: String): Boolean {
        val exists = userRepository.existsByEmail(email)
        logger.debug("User with email `$email` exists: `$exists`")
        return exists
    }

    fun userExistsByUsernameOrEmail(username: String, email: String): Boolean {
        val exists = userRepository.existsByUsernameOrEmail(username, email)
        logger.debug("User with username `$username` or email `$email` exists: `$exists`")
        return exists
    }

    fun deleteUser(id: UUID) {
        userRepository.deleteById(id)
        logger.info("User with id `$id` has been deleted")
    }
}