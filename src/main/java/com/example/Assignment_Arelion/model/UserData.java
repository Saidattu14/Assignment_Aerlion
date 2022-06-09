package com.example.Assignment_Arelion.model;



import com.example.Assignment_Arelion.Enums.AccountStatusinfo;

import java.time.LocalTime;


public class UserData {

    private String username;
    private String password;
    private int noOfRequestsPerMinute;
    private LocalTime lastRequestTime;
    private AccountStatusinfo accountStatusinfo;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountStatusinfo getAccountStatusinfo() {
        return accountStatusinfo;
    }

    public void setAccountStatusinfo(AccountStatusinfo accountStatusinfo) {
        this.accountStatusinfo = accountStatusinfo;
    }

    public int getNoOfRequestsPerMinute() {
        return noOfRequestsPerMinute;
    }

    public void setNoOfRequestsPerMinute(int noOfRequestsPerMinute) {
        this.noOfRequestsPerMinute = noOfRequestsPerMinute;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalTime getLastRequestTime() {
        return lastRequestTime;
    }

    public void setLastRequestTime(LocalTime lastRequestTime) {
        this.lastRequestTime = lastRequestTime;
    }

    /**
     * This is a class constructor where is assign userdata for the class variables.
     */
    public UserData(String username, String password, int noOfRequestsPerMinute, LocalTime lastRequestTime, AccountStatusinfo accountStatusinfo)
    {
        this.username = username;
        this.password = password;
        this.noOfRequestsPerMinute = noOfRequestsPerMinute;
        this.lastRequestTime = lastRequestTime;
        this.accountStatusinfo = accountStatusinfo;
    }
}