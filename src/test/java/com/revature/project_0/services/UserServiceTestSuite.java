package com.revature.project_0.services;

import com.revature.project_0.daos.UserDAO;
import com.revature.project_0.exceptions.InvalidCredentialException;
import com.revature.project_0.exceptions.InvalidRequestException;
import com.revature.project_0.exceptions.ResourcePersistenceException;
import com.revature.project_0.models.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class UserServiceTestSuite {

    private UserService sut;
    private UserDAO mockUserDAO;

    @Before
    public void testCaseSetup() {
        mockUserDAO = mock(UserDAO.class);
        sut = new UserService(mockUserDAO);

    }

    @After
    public void testCaseCleanup() {
        sut = null;
    }

    @Test
    public void test_isUserValid_returnsTrue_givenValidUser() {
        // Arrange
        User validUser = new User("valid", "valid", "valid", "valid", "valid");

        // Act
        boolean actualResult = sut.isUserValid(validUser);

        // Assert
        Assert.assertTrue("Expected user to be considered valid", actualResult);

    }

    @Test
    public void test_isUserValid_returnsFalse_givenUserWithInvalidFirstName() {

        // Arrange
        User invalidUser_1 = new User(null, "valid", "valid", "valid", "valid");
        User invalidUser_2 = new User("", "valid", "valid", "valid", "valid");
        User invalidUser_3 = new User("             ", "valid", "valid", "valid", "valid");

        // Act
        boolean actualResult_1 = sut.isUserValid(invalidUser_1);
        boolean actualResult_2 = sut.isUserValid(invalidUser_2);
        boolean actualResult_3 = sut.isUserValid(invalidUser_3);

        // Assert
        Assert.assertFalse("Expected user to be considered false.", actualResult_1);
        Assert.assertFalse("Expected user to be considered false.", actualResult_2);
        Assert.assertFalse("Expected user to be considered false.", actualResult_3);

    }

    @Test
    public void test_isUserValid_returnsFalse_givenUserWithInvalidLastName() {

        // Arrange
        User invalidUser_1 = new User("valid", null, "valid", "valid", "valid");
        User invalidUser_2 = new User("valid", "", "valid", "valid", "valid");
        User invalidUser_3 = new User("valid", "     ", "valid", "valid", "valid");

        // Act
        boolean actualResult_1 = sut.isUserValid(invalidUser_1);
        boolean actualResult_2 = sut.isUserValid(invalidUser_2);
        boolean actualResult_3 = sut.isUserValid(invalidUser_3);

        // Assert
        Assert.assertFalse("Expected user to be considered false.", actualResult_1);
        Assert.assertFalse("Expected user to be considered false.", actualResult_2);
        Assert.assertFalse("Expected user to be considered false.", actualResult_3);

    }

    @Test
    public void test_isUserValid_returnsFalse_givenUserWithInvalidEmail() {

        // Arrange
        User invalidUser_1 = new User("valid", "valid", null, "valid", "valid");
        User invalidUser_2 = new User("valid", "valid", "", "valid", "valid");
        User invalidUser_3 = new User("valid", "valid", "      ", "valid", "valid");

        // Act
        boolean actualResult_1 = sut.isUserValid(invalidUser_1);
        boolean actualResult_2 = sut.isUserValid(invalidUser_2);
        boolean actualResult_3 = sut.isUserValid(invalidUser_3);

        // Assert
        Assert.assertFalse("Expected user to be considered false.", actualResult_1);
        Assert.assertFalse("Expected user to be considered false.", actualResult_2);
        Assert.assertFalse("Expected user to be considered false.", actualResult_3);

    }

    @Test
    public void test_isUserValid_returnsFalse_givenUserWithInvalidUsername() {

        // Arrange
        User invalidUser_1 = new User("valid", "valid", "valid", null, "valid");
        User invalidUser_2 = new User("valid", "valid", "valid", "", "valid");
        User invalidUser_3 = new User("valid", "valid", "valid", "     ", "valid");

        // Act
        boolean actualResult_1 = sut.isUserValid(invalidUser_1);
        boolean actualResult_2 = sut.isUserValid(invalidUser_2);
        boolean actualResult_3 = sut.isUserValid(invalidUser_3);

        // Assert
        Assert.assertFalse("Expected user to be considered false.", actualResult_1);
        Assert.assertFalse("Expected user to be considered false.", actualResult_2);
        Assert.assertFalse("Expected user to be considered false.", actualResult_3);

    }

    @Test
    public void test_isUserValid_returnsFalse_givenUserWithInvalidPassword() {

        // Arrange
        User invalidUser_1 = new User("valid", "valid", "valid", "valid", null);
        User invalidUser_2 = new User("valid", "valid", "valid", "valid", "");
        User invalidUser_3 = new User("valid", "valid", "valid", "valid", "     ");

        // Act
        boolean actualResult_1 = sut.isUserValid(invalidUser_1);
        boolean actualResult_2 = sut.isUserValid(invalidUser_2);
        boolean actualResult_3 = sut.isUserValid(invalidUser_3);

        // Assert
        Assert.assertFalse("Expected user to be considered false.", actualResult_1);
        Assert.assertFalse("Expected user to be considered false.", actualResult_2);
        Assert.assertFalse("Expected user to be considered false.", actualResult_3);

    }

    @Test
    public void test_register_returnsTrue_givenValidUser() {

        // Arrange
        User validUser = new User("valid", "valid", "valid", "valid", "valid");
        when(mockUserDAO.findUserByUsername(validUser.getUsername())).thenReturn(null);
        when(mockUserDAO.findUserByEmail(validUser.getEmail())).thenReturn(null);
        when(mockUserDAO.save(validUser)).thenReturn(validUser);

        // Act
        boolean actualResult = sut.register(validUser);

        // Assert
        Assert.assertTrue("Expected result to be true with valid user provided.", actualResult);
        verify(mockUserDAO, times(1)).save(validUser);
        verify(mockUserDAO, times(1)).findUserByUsername("valid");
        verify(mockUserDAO, times(1)).findUserByEmail("valid");

    }

    @Test (expected = InvalidCredentialException.class)
    public void test_register_throwsInvalidCredentialException_givenInvalidUser() {

        // Arrange
        User invalidUser = new User("", "valid", "valid", "valid", "valid");

        // Act
        try {
            boolean actualResult = sut.register(invalidUser);
        } finally {
            verify(mockUserDAO, times(0)).save(invalidUser);
            verify(mockUserDAO, times(0)).findUserByUsername("");
            verify(mockUserDAO, times(0)).findUserByEmail("valid");
        }

        // Assert


    }

    @Test (expected = ResourcePersistenceException.class)
    public void test_register_throwsResourcePersistenceException_givenUserWithTakenUsername() {

        // Arrange
        User takenUser = new User("valid", "valid", "valid", "valid", "valid");

        when(mockUserDAO.findUserByUsername(takenUser.getUsername())).thenReturn(new User("","","","",""));
        when(mockUserDAO.findUserByEmail(takenUser.getEmail())).thenReturn(null);
        when(mockUserDAO.save(takenUser)).thenReturn(takenUser);

        // Act
        try {
            boolean actualResult = sut.register(takenUser);
        } finally {
            verify(mockUserDAO, times(0)).save(takenUser);
            verify(mockUserDAO, times(1)).findUserByUsername("valid");
            verify(mockUserDAO, times(1)).findUserByEmail("valid");
        }

        // Assert


    }

    @Test (expected = ResourcePersistenceException.class)
    public void test_register_throwsResourcePersistenceException_givenUserWithTakenEmail() {

        // Arrange
        User takenUser = new User("valid", "valid", "valid", "valid", "valid");

        when(mockUserDAO.findUserByUsername(takenUser.getUsername())).thenReturn(null);
        when(mockUserDAO.findUserByEmail(takenUser.getEmail())).thenReturn(new User("","","","",""));
        when(mockUserDAO.save(takenUser)).thenReturn(takenUser);

        // Act
        try {
            boolean actualResult = sut.register(takenUser);
        } finally {
            verify(mockUserDAO, times(0)).save(takenUser);
            verify(mockUserDAO, times(1)).findUserByUsername("valid");
            verify(mockUserDAO, times(1)).findUserByEmail("valid");
        }

        // Assert


    }

    @Test
    public void test_authenticate_assignsCurrentUser_givenValidUserInfo() {

        // Arrange
        String validName = "valid";
        String validPassword = "valid";

        when(mockUserDAO.findUserByUsernameAndPassword(validName, validPassword)).thenReturn(new User("","","","",""));

        // Act
        sut.authenticate(validName, validPassword);

        // Assert
        Assert.assertNotNull(sut.getCurrentUser());

        verify(mockUserDAO, times(1)).findUserByUsernameAndPassword(validName, validPassword);

    }

    @Test
    public void test_authenticate_doesNotAssignCurrentUser_givenInvalidUserInfo() {

        // Arrange
        String validName = "invalid";
        String validPassword = "valid";

        when(mockUserDAO.findUserByUsernameAndPassword(validName, validPassword)).thenReturn(null);

        // Act
        sut.authenticate(validName, validPassword);

        // Assert
        Assert.assertNull(sut.getCurrentUser());

        verify(mockUserDAO, times(1)).findUserByUsernameAndPassword(validName, validPassword);

    }

}
