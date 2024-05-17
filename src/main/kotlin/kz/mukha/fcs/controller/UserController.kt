package kz.mukha.fcs.controller

import kz.mukha.fcs.dto.UserDto
import kz.mukha.fcs.service.UserService
import org.springframework.http.ResponseEntity
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

    @PostMapping("/register")
    fun registerUser(@RequestBody userDto: UserDto): ResponseEntity<UserDto> {
        val user = userService.registerUser(userDto.username, userDto.password)
        return ResponseEntity.ok(UserDto(user.username, user.password))
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: UUID): ResponseEntity<String> {
        userService.deleteUser(id)
        return ResponseEntity.ok("User with id $id was deleted")
    }
}