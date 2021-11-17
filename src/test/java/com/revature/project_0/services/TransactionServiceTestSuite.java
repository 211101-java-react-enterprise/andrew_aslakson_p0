package com.revature.project_0.services;

import com.revature.project_0.daos.TransactionDAO;
import com.revature.project_0.exceptions.InvalidCredentialException;
import com.revature.project_0.models.Transaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TransactionServiceTestSuite {

    private TransactionService sut;
    private TransactionDAO mockTransactionDAO;

    @Before
    public void testCaseSetup() {
        mockTransactionDAO = mock(TransactionDAO.class);
        sut = new TransactionService(mockTransactionDAO);

    }

    @After
    public void testCaseCleanup() {
        sut = null;
    }

    @Test
    public void test_isTransactionValid_returnsTrue_givenValidTransaction() {
        // Arrange
        Transaction validTransaction = new Transaction(true, new Timestamp(System.currentTimeMillis()), 1.00, 100.00);

        // Act
        boolean actualResult = sut.isTransactionValid(validTransaction);

        // Assert
        Assert.assertTrue("Expected transaction to be considered valid", actualResult);

    }

    @Test
    public void test_isTransactionValid_returnsFalse_givenTransactionWithInvalidValues() {

        // Arrange
        Transaction invalidTransaction_1 = new Transaction(false, new Timestamp(System.currentTimeMillis()), 100, 1);
        Transaction invalidTransaction_2 = new Transaction(false, new Timestamp(System.currentTimeMillis()), 0, 1);
        Transaction invalidTransaction_3 = new Transaction(true, new Timestamp(System.currentTimeMillis()), -10, 100);
        Transaction invalidTransaction_4 = new Transaction(true, new Timestamp(System.currentTimeMillis()), 0, 1);
        Transaction invalidTransaction_5 = new Transaction(false, new Timestamp(System.currentTimeMillis()), -10, 1);

        // Act
        boolean actualResult_1 = sut.isTransactionValid(invalidTransaction_1);
        boolean actualResult_2 = sut.isTransactionValid(invalidTransaction_2);
        boolean actualResult_3 = sut.isTransactionValid(invalidTransaction_3);
        boolean actualResult_4 = sut.isTransactionValid(invalidTransaction_4);
        boolean actualResult_5 = sut.isTransactionValid(invalidTransaction_5);

        // Assert
        Assert.assertFalse("Expected transaction to be considered false.", actualResult_1);
        Assert.assertFalse("Expected transaction to be considered false.", actualResult_2);
        Assert.assertFalse("Expected transaction to be considered false.", actualResult_3);
        Assert.assertFalse("Expected transaction to be considered false.", actualResult_4);
        Assert.assertFalse("Expected transaction to be considered false.", actualResult_5);

    }

    @Test
    public void test_register_returnsTransaction_givenValidTransaction() {

        // Arrange
        Transaction validTransaction = new Transaction(true, new Timestamp(System.currentTimeMillis()), 1.00, 100.00);
        when(mockTransactionDAO.save(validTransaction)).thenReturn(new Transaction(true, new Timestamp(System.currentTimeMillis()), 1.00, 100.00));

        // Act
        Transaction actualResult = sut.register(validTransaction);

        // Assert
        Assert.assertNotNull(actualResult);
        verify(mockTransactionDAO, times(1)).save(validTransaction);

    }

    @Test (expected = InvalidCredentialException.class)
    public void test_register_throwsInvalidCredentialException_givenInvalidTransaction() {

        // Arrange
        Transaction invalidTransaction = new Transaction(false, new Timestamp(System.currentTimeMillis()), 100, 1);

        when(mockTransactionDAO.save(invalidTransaction)).thenReturn(new Transaction(true, new Timestamp(System.currentTimeMillis()), 100, 1));

        // Act
        try {
            Transaction actualResult = sut.register(invalidTransaction);
        } finally {
            verify(mockTransactionDAO, times(0)).save(invalidTransaction);
        }

        // Assert


    }

}
