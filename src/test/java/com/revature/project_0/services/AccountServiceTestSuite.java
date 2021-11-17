package com.revature.project_0.services;

import com.revature.project_0.daos.AccountDAO;
import com.revature.project_0.exceptions.InvalidRequestException;
import com.revature.project_0.exceptions.ResourcePersistenceException;
import com.revature.project_0.models.Transaction;
import com.revature.project_0.models.accounts.Account;
import com.revature.project_0.models.accounts.CheckingAccount;
import com.revature.project_0.models.accounts.SavingsAccount;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.mockito.Mockito.*;

public class AccountServiceTestSuite {

    private AccountService sut;
    private AccountDAO mockAccountDao;

    @Before
    public void testCaseSetup() {
        mockAccountDao = mock(AccountDAO.class);
        sut = new AccountService(mockAccountDao);

    }

    @After
    public void testCaseCleanup() {
        sut = null;
    }

    @Test
    public void test_isAccountValid_returnsTrue_givenValidAccount() {
        // Arrange
        Account validAccount = new CheckingAccount("valid", 0.0);

        // Act
        boolean actualResult = sut.isAccountValid(validAccount);

        // Assert
        Assert.assertTrue("Expected Account to be considered valid", actualResult);

    }

    @Test
    public void test_isAccountValid_returnsFalse_givenInvalidAccount() {
        // Arrange
        Account invalidAccount_1 = null;
        Account invalidAccount_2 = new CheckingAccount("", 0.0);
        Account invalidAccount_3 = new CheckingAccount("    ", 0.0);

        // Act
        boolean actualResult_1 = sut.isAccountValid(invalidAccount_1);
        boolean actualResult_2 = sut.isAccountValid(invalidAccount_2);
        boolean actualResult_3 = sut.isAccountValid(invalidAccount_3);

        // Assert
        Assert.assertFalse("Expected Account to be considered invalid", actualResult_1);
        Assert.assertFalse("Expected Account to be considered invalid", actualResult_2);
        Assert.assertFalse("Expected Account to be considered invalid", actualResult_3);

    }

    @Test
    public void test_linkAccountToUser_returnsTrue_givenValidCredentials() {
        // Arrange
        String validUserUUID = "valid";
        String validAccountUUID = "valid";

        when(mockAccountDao.linkAccountToUser(validUserUUID, validAccountUUID)).thenReturn(true);
        // Act
        boolean actualResult = sut.linkAccountToUser(validUserUUID, validAccountUUID);

        // Assert
        Assert.assertTrue("Expected Credentials to be considered valid", actualResult);

    }

    @Test
    public void test_linkAccountToUser_returnsFalse_givenExistingLink() {
        // Arrange
        String existingPairUserUUID = "valid but already exists";
        String existingPairAccountUUID = "valid but already exists";

        when(mockAccountDao.linkAccountToUser(existingPairUserUUID, existingPairAccountUUID)).thenReturn(false);

        // Act
        boolean actualResult = sut.linkAccountToUser(existingPairAccountUUID, existingPairUserUUID);

        // Assert
        Assert.assertFalse("Expected Credentials to be considered invalid", actualResult);

    }

    @Test
    public void test_register_returnsAccount_givenValidData() {
        // Arrange
        Account validAccount = new CheckingAccount("Valid", 0.0);
        validAccount.setAccountUUID("Valid");
        String validUserUUID = "valid";

        when(mockAccountDao.doesUserIDHaveAccountName(validUserUUID, validAccount.getName())).thenReturn(false);
        when(mockAccountDao.save(validAccount)).thenReturn(validAccount);
        when(mockAccountDao.linkAccountToUser(validUserUUID, validAccount.getAccountUUID())).thenReturn(true);

        // Act
        Account newAccount = sut.register(validAccount, validUserUUID);

        // Assert
        Assert.assertNotNull(newAccount);

        verify(mockAccountDao, times( 1 )).doesUserIDHaveAccountName(validUserUUID, validAccount.getName());
        verify(mockAccountDao, times( 1 )).save(validAccount);
        verify(mockAccountDao, times( 1 )).linkAccountToUser(validUserUUID, validAccount.getAccountUUID());

    }

    @Test (expected = InvalidRequestException.class)
    public void test_register_throwsInvalidRequestException_givenInvalidCredentials() {
        // Arrange
        Account invalidAccount = new CheckingAccount("", 0.0);
        invalidAccount.setAccountUUID("Valid");
        String validUserUUID = "valid";

        when(mockAccountDao.doesUserIDHaveAccountName(validUserUUID, invalidAccount.getName())).thenReturn(false);
        when(mockAccountDao.save(invalidAccount)).thenReturn(invalidAccount);
        when(mockAccountDao.linkAccountToUser(validUserUUID, invalidAccount.getAccountUUID())).thenReturn(false);

        // Act
        try {
            sut.register(invalidAccount, validUserUUID);
        } finally {
            // Assert
            verify(mockAccountDao, times(0)).doesUserIDHaveAccountName(validUserUUID, invalidAccount.getName());
            verify(mockAccountDao, times(0)).save(invalidAccount);
            verify(mockAccountDao, times(0)).linkAccountToUser(validUserUUID, invalidAccount.getAccountUUID());
        }
    }

    @Test (expected = ResourcePersistenceException.class)
    public void test_register_throwsResourcePersistenceException_givenDataFailsToBePersisted() {
        // Arrange
        Account validAccount = new CheckingAccount("Valid", 0.0);
        validAccount.setAccountUUID("Valid");
        String validUserUUID = "valid";

        when(mockAccountDao.doesUserIDHaveAccountName(validUserUUID, validAccount.getName())).thenReturn(false);
        when(mockAccountDao.save(validAccount)).thenReturn(validAccount);
        when(mockAccountDao.linkAccountToUser(validUserUUID, validAccount.getAccountUUID())).thenReturn(false);

        // Act
        try {
            sut.register(validAccount, validUserUUID);
        } finally {
            // Assert
            verify(mockAccountDao, times(1)).doesUserIDHaveAccountName(validUserUUID, validAccount.getName());
            verify(mockAccountDao, times(1)).save(validAccount);
            verify(mockAccountDao, times(1)).linkAccountToUser(validUserUUID, validAccount.getAccountUUID());
        }
    }

    @Test (expected = InvalidRequestException.class)
    public void test_register_throwsInvalidRequestException_givenUserAlreadyHasAccountWithName() {
        // Arrange
        Account validAccount = new CheckingAccount("Valid", 0.0);
        validAccount.setAccountUUID("Valid");
        String validUserUUID = "valid";

        when(mockAccountDao.doesUserIDHaveAccountName(validUserUUID, validAccount.getName())).thenReturn(true);
        when(mockAccountDao.save(validAccount)).thenReturn(validAccount);
        when(mockAccountDao.linkAccountToUser(validUserUUID, validAccount.getAccountUUID())).thenReturn(true);

        // Act
        Account newAccount = sut.register(validAccount, validUserUUID);

        // Assert
        Assert.assertNotNull(newAccount);

        verify(mockAccountDao, times( 1 )).doesUserIDHaveAccountName(validUserUUID, validAccount.getName());
        verify(mockAccountDao, times( 0 )).save(validAccount);
        verify(mockAccountDao, times( 0 )).linkAccountToUser(validUserUUID, validAccount.getAccountUUID());

    }

}
