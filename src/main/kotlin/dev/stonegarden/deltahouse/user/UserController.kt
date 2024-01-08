package dev.stonegarden.deltahouse.user

import dev.stonegarden.deltahouse.legacy.LegacyRepository
import dev.stonegarden.deltahouse.legacy.LegacyUserDAO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/api/v1/users"])
class UserController(
    @Autowired val userService: UserService,
    @Autowired val legacyRepository: LegacyRepository,
) {
    @Operation(description = "Get legacy users")
    @GetMapping("/legacy", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getLegacy(): List<LegacyUserDAO> {
        return legacyRepository.getUsers()
    }

    @Operation(description = "Get all users")
    @GetMapping("", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUsers(): List<UserDTO> {
        return userService.getAllUsers().map { UserDTO(it) }
    }

    @Operation(description = "Get user by cardId")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "User found",
            content = [Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE, schema = Schema(implementation = UserDTO::class)
            )],
        ), ApiResponse(responseCode = "404", description = "User not found", content = [])]
    )
    @GetMapping("/{cardId}")
    fun getUserByCardId(@PathVariable cardId: Long): UserDTO {
        return UserDTO(userService.getUserByCardId(cardId))
    }

    @PostMapping("", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createUser(@RequestBody newUser: UserDTO): UserDTO {
        return UserDTO(userService.createUser(User(newUser), "new"))
    }

    @PatchMapping("", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateUserDetails(@RequestBody updatedUser: UserDTO): UserDTO {
        return UserDTO(userService.updateUserDetails(User(updatedUser), "changedBy"))
    }

    @PatchMapping("/updateCardId", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun changeUserCardId(@RequestParam email: String, @RequestParam newCardId: Long): UserDTO {
        return UserDTO(userService.changeUserCardId(email, newCardId, "changedBy"))
    }

    @DeleteMapping("/{cardId}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteUser(@PathVariable cardId: Long) {
        userService.deleteUser(cardId)
    }

}