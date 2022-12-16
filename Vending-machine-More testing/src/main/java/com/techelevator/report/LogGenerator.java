package com.techelevator.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogGenerator
{
    File filePath = new File("Log.txt");
    String activityLog, dateAndTime;
    BigDecimal amount, remainingBalance;

    public String writeToLog(String activityLog, BigDecimal amount,BigDecimal remainingBalance) {
        //get date and time
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
        dateAndTime = formatter.format(date);

        this.activityLog = activityLog;
        this.amount = amount;
        this.remainingBalance = remainingBalance;

        String outputLine = dateAndTime + " " + activityLog + " $" + amount + " $" + remainingBalance;

        //write to file
        try (PrintWriter dataOutput = new PrintWriter (new FileOutputStream(filePath, true))) {
            dataOutput.println(outputLine);
            dataOutput.flush();
        }
        catch (Exception ex) {
            System.out.println("Error! Logging exception.");
            System.out.println(ex.getMessage());
        }
        return outputLine;
    }
}
