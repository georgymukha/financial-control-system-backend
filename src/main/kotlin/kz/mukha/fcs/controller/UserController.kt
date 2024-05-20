package kz.mukha.fcs.controller

import kz.mukha.fcs.dto.UserDto
import kz.mukha.fcs.service.UserService
import kz.mukha.fcs.util.LoggerUtil.getLogger
import kz.mukha.fcs.util.ResponseCode
import kz.mukha.fcs.util.ResponseUtil.createResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService,
) {

    private val logger = getLogger(this::class.java)

    @PostMapping("/register")
    fun registerUser(@RequestBody userDto: UserDto): ResponseEntity<Map<String, Any>> {
        if (userService.userExistsByUsernameOrEmail(userDto.username, userDto.email)) {
            logger.warn("Attempt to register with existing username or email: `${userDto.username}`, `${userDto.email}`")
            return createResponse(
                httpStatus = HttpStatus.CONFLICT,
                responseCode = ResponseCode.USER_EXISTS,
            )
        }
        val user = userService.registerUser(userDto.username, userDto.password, userDto.email)
        logger.info("User `${user.username}` registered successfully with email ${user.email}")
        return createResponse(
            httpStatus = HttpStatus.OK,
            responseCode = ResponseCode.USER_CREATED,
        )
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: UUID): ResponseEntity<Map<String, Any>> {
        val currentUser = SecurityContextHolder.getContext().authentication.principal as UserDetails
        val username = currentUser.username
        if (username != userService.findUserById(id).username) throw IllegalAccessException("Attempt to delete user with id `$id` from another user `$username`")

        if (userService.userExistsById(id)) {
            userService.deleteUser(id)
            logger.info("User with id `$id` deleted successfully")
            return createResponse(
                httpStatus = HttpStatus.OK,
                responseCode = ResponseCode.USER_DELETED
            )
        } else {
            logger.warn("Attempt to delete non-existing user with id `$id`")
            return createResponse(
                httpStatus = HttpStatus.NOT_FOUND,
                responseCode = ResponseCode.USER_NOT_FOUND
            )
        }
    }
}