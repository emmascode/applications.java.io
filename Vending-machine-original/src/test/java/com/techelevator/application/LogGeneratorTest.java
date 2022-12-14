package com.techelevator.application;

import com.techelevator.report.LogGenerator;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class LogGeneratorTest
{
    // initializers
    LogGenerator logGenerator = new LogGenerator();
    Date date = new Date();
    String actual, expected;

    // variables
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
    String activityLog, dateAndTime = formatter.format(date);
    BigDecimal amount, remainingBalance;

    // tests
    @Test
    public void writeToLog_Should_WriteToLog_With_DateAndTime() {
        // arrange
        amount = BigDecimal.valueOf(5.00);
        remainingBalance = BigDecimal.valueOf(5.00);
        activityLog = "FEED ME";
        expected = dateAndTime + " " + activityLog + " $" + amount + " $" + remainingBalance;

        // act
        actual = logGenerator.writeToLog(activityLog, amount, remainingBalance);

        // assert
        assertEquals("Time and date should be correct." +
                    "Remaining balance should be $5.00 each", expected, actual);

    }
}
