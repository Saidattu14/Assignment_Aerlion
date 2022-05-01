package com.example.Assignment_Aerlion.model;



import com.example.Assignment_Aerlion.Enums.AccountStatusinfo;

import java.time.LocalTime;


public class UserData {

    private String username;
    private String password;
    private int noofrequestsperminute;
    private LocalTime lastrequesttime;
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

    public int getNoofrequestsperminute() {
        return noofrequestsperminute;
    }

    public void setNoofrequestsperminute(int noofrequestsperminute) {
        this.noofrequestsperminute = noofrequestsperminute;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalTime getLastrequesttime() {
        return lastrequesttime;
    }

    public void setLastrequesttime(LocalTime lastrequesttime) {
        this.lastrequesttime = lastrequesttime;
    }

    /**
     * This is a class constructor where is assign userdata for the class variables.
     */
    public UserData(String username, String password, int noofrequestsperminute, LocalTime lastrequesttime, AccountStatusinfo accountStatusinfo)
    {
        this.username = username;
        this.password = password;
        this.noofrequestsperminute = noofrequestsperminute;
        this.lastrequesttime = lastrequesttime;
        this.accountStatusinfo = accountStatusinfo;
    }
}