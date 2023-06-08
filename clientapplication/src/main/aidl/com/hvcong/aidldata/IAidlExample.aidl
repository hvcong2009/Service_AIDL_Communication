// IAidlExample.aidl
package com.hvcong.aidldata;
import com.hvcong.aidldata.Account;
// Bắt buộc package phải giống với package ở phía java và giống với package ở phía client

interface IAidlExample {
    // Client get data from server
    int getNumber();
    Account getAccount();
    List<Account> getListAccount();

    // Client send data to Server
    void sendNumber(int num1);
    // In case object need using "in" keyword
    void setAccount(in Account account);
    void setListAccount(in List<Account> listAccount);
}