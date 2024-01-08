package dev.stonegarden.deltahouse.wallet

import dev.stonegarden.deltahouse.user.UserDAO
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import java.time.ZonedDateTime
import java.util.*

@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
@WebMvcTest(controllers = [WalletController::class])
@WithMockUser
internal class WalletControllerTest(@Autowired val mockMvc: MockMvc) {
    private val testUserA = UserDAO(
        id = 1,
        cardId = 10,
        firstName = "Test A",
        lastName = "Testusen",
        email = "a@test.com",
        birthday = ZonedDateTime.now().minusYears(20),
        userGroup = "BFY",
        isMember = true,
        userProperties = Collections.emptySet(),
        createdBy = "Test"
    )

    private val walletA = WalletDAO(
        id = 1,
        user = testUserA,
        cashBalance = 0,
        totalSpent = 0,
    )

    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun mockWalletService() = mockk<WalletService>()
    }

    @Autowired
    private lateinit var mockWalletService: WalletService

    @Test
    fun purchase() {
        every { mockWalletService.purchase(any(), any()) } returns UserWallet(testUserA, walletA)
        mockMvc
            .post(urlTemplate = "/users/123/wallet/buy?value=100") { with(csrf()) }
            .andExpect {
                status { isOk() }
            }
    }
}