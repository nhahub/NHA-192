package com.example.MovieApp.ViewModels.Auth

import com.example.MovieApp.auth.AuthResult
import com.example.MovieApp.auth.EmailPasswordAuthManagerRepository
import com.example.MovieApp.viewModels.AuthViewModel
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest

class AuthViewModelTest {
    private val mockRepo = mockk<EmailPasswordAuthManagerRepository>(relaxed = true)


    //test for onEmailChange()
    @Test
    fun test() = runBlocking {
        // GIVEN
        val viewModel = AuthViewModel(mockRepo)
        val expectedEmail = "test@example.com"

        // WHEN
        viewModel.onEmailChange(expectedEmail)

        // THEN
        val result = viewModel.email.first()
        assertEquals(expectedEmail, result)
    }
    //given new email input when onEmailChange called then email state updates


    //test for onPasswordChange()
    @Test
    fun test1() =
        runBlocking {
            // GIVEN
            val viewModel = AuthViewModel(mockRepo)
            val expectedPassword = "MyStrongPass123"

            // WHEN
            viewModel.onPasswordChange(expectedPassword)

            // THEN
            val result = viewModel.password.first()
            assertEquals(expectedPassword, result)
        }
    // given new password input when onPasswordChange called then password state updates

    //onPhoneNumberChange()
    @Test
    fun test2() =
        runBlocking {
            // GIVEN
            val viewModel = AuthViewModel(mockRepo)
            val expectedPhone = "0123456789"

            // WHEN
            viewModel.onPhoneNumberChange(expectedPhone)

            // THEN
            val result = viewModel.phoneNumber.first()
            assertEquals(expectedPhone, result)
        }
    //given new phone number when onPhoneNumberChange called then phone state updates


    //onPasswordChange()
    @Test
    fun test3() =
        runBlocking {
            // GIVEN
            val viewModel = AuthViewModel(mockRepo)
            val expectedPassword = "StrongPass123"

            // WHEN
            viewModel.onPasswordChange(expectedPassword)

            // THEN
            val result = viewModel.password.first()
            assertEquals(expectedPassword, result)
        }
    //given new password when onPasswordChange called then password state updates


    //SignIp()
    @Test
    fun test4() = runTest {
        // GIVEN
        val viewModel = AuthViewModel(mockRepo)
        val email = "test@example.com"
        val password = "123456"
        val expectedResult = AuthResult.Success("OK")

        coEvery { mockRepo.signIn(email, password) } returns expectedResult

        // WHEN
        viewModel.SignIn(email, password)

        // THEN
        val result = viewModel.signInState.first()
        assertEquals(expectedResult, result)
    }
    //given valid credentials when SignIn called then emits success

    @Test
    fun test5() = runTest {
        // GIVEN
        val viewModel = AuthViewModel(mockRepo)
        val email = "wrong@example.com"
        val password = "badpass"

        coEvery { mockRepo.signIn(email, password) } throws Exception("Sign in failed")

        // WHEN
        viewModel.SignIn(email, password)

        // THEN
        val result = viewModel.signInState.first()
        assert(result is AuthResult.Error)
        assertEquals("Sign in failed", (result as AuthResult.Error).message)
    }
    //given repo throws exception when SignIn called then emits error

    //SignUp()
    @Test
    fun test6() = runTest {
        // GIVEN
        val viewModel = AuthViewModel(mockRepo)
        val email = "test@example.com"
        val password = "123456"
        val name = "Anyname"
        val phone = "01000000000"

        val expectedResult = AuthResult.Success("User created")

        coEvery { mockRepo.signUp(email, password, name, phone) } returns expectedResult

        // WHEN
        viewModel.SignUp(email, password, name, phone)
        // THEN
        val result = viewModel.signupState.first()
        assertEquals(expectedResult, result)
    }
    //given valid inputs when SignUp called then emits success

    @Test
    fun test7() = runTest {
        // GIVEN
        val viewModel = AuthViewModel(mockRepo)
        val email = "bad@example.com"
        val password = "wrong"
        val name = "Bad User"
        val phone = "01111111111"

        coEvery { mockRepo.signUp(email, password, name, phone) } throws Exception("Sign up failed")

        // WHEN
        viewModel.SignUp(email, password, name, phone)

        // THEN
        val result = viewModel.signupState.first()
        assert(result is AuthResult.Error)
        assertEquals("Sign up failed", (result as AuthResult.Error).message)
    }
    //given repo throws exception when SignUp called then emits error


}