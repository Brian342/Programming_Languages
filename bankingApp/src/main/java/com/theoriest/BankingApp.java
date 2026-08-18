package com.theoriest.greendaybank;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

import com.theoriest.greendaybank.service.BankingService;

public class BankingApp {
    public static void main(String[] args) {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true));
        BankingService bankingService = new BankingService();
        bankingService.run();
    }
    // this cod e
}