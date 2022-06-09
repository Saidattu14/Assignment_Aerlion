package com.example.Assignment_Arelion.Services;
import com.example.Assignment_Arelion.Enums.AccountStatusinfo;
import com.example.Assignment_Arelion.model.UserData;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.util.HashMap;


@Service
public class AuthService {

    public HashMap<String, UserData> usersMap = new HashMap<>();

    /**
     * This method returns boolean value.
     * This Method validate Sign In details for the UserAccount When the user tries to regester the data.
     * @param @authentication
     * @return true if no such useraccount details found else false for similar data.
     */
    public boolean registerAuthentication(final Authentication authentication)
    {
        final String name = authentication.getName();
        if(usersMap.get(name) != null)
        {
            UserData u = usersMap.get(name);
            if(u.getAccountStatusinfo() == AccountStatusinfo.Created)
            {
                u.setAccountStatusinfo(AccountStatusinfo.Actived);
                return true;
            }
            return false;
        }
        return false;
    }


    /**
     * This method returns true if the user exceeds the maximum request per minute.
     * @param @authentication
     * @return true if exceeds else false
     */
    public boolean rateLimitingRequests(final Authentication authentication)
    {
        final String name = authentication.getName();
        if(usersMap.get(name) != null)
        {
            UserData u = usersMap.get(name);
            int lastRequestTimeData = Integer.parseInt(getLastRequestTime(u));
            int currentRequestTimeData = Integer.parseInt(getCurrentRequestTime(u));
            if(lastRequestTimeData > currentRequestTimeData)
            {
                currentRequestTimeData = lastRequestTimeData + currentRequestTimeData;
            }
            if(currentRequestTimeData - lastRequestTimeData >= 60)
            {
                u.setLastRequestTime(LocalTime.now());
                u.setNoOfRequestsPerMinute(1);
            }
            else if(currentRequestTimeData - lastRequestTimeData < 60)
            {
                if(u.getNoOfRequestsPerMinute() <=5)
                {
                    u.setNoOfRequestsPerMinute(u.getNoOfRequestsPerMinute()+1);
                }
                else {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * This method get the currentRequestTime API call time of the user data.
     * @param @UserData
     * @return time value in String.
     */
    private String getCurrentRequestTime(UserData u)
    {
        String currentRequestTimeStr[] = LocalTime.now().toString().split(":");
        String currentRequestTime = "";
        for(int i = 0; i<currentRequestTimeStr.length;i++)
        {
            if(i != 2) {
                currentRequestTime = currentRequestTime + currentRequestTimeStr[i];
            }
            else
            {
                String seconds[] = currentRequestTimeStr[i].split("[.]");
                currentRequestTime = currentRequestTime + seconds[0];

            }
        }
        return currentRequestTime;
    }

    /**
     * This method get the lastRequestTime API call time of the user data.
     * @param @UserData
     * @return time value in String.
     */
    private String getLastRequestTime(UserData u)
    {
        String lastRequestRimeStrArr [] = u.getLastRequestTime().toString().split(":");
        String lastRequestTime = "";
        for(int i = 0; i<lastRequestRimeStrArr.length;i++)
        {
            if(i != 2) {
                lastRequestTime = lastRequestTime +lastRequestRimeStrArr[i];
            }
            else
            {
                String seconds[] = lastRequestRimeStrArr[i].split("[.]");
                lastRequestTime = lastRequestTime + seconds[0];
            }
        }
        return lastRequestTime;
    }


    /**
     * This method validates the User Account data when user tries to access the API data.
     * Main Purpose is to activate the UserData when the user SignIn
     * @param authentication
     * @return true if the user has a registed Account Else it returns false.
     */
    public boolean loginAuthentication(final Authentication authentication)
    {
        final String name = authentication.getName();
        if(usersMap.get(name) != null)
        {
            UserData u = usersMap.get(name);
            if(u.getAccountStatusinfo() == AccountStatusinfo.Actived)
            {
                return true;
            }
            usersMap.remove(u);
        }
        return false;
    }

    /**
     * This method validates the data at the Initial Authentiction Level.
     * @param @authentication
     * @return true if user is authorised else false
     */
    public boolean userDataAuthentication(final Authentication authentication)
    {
        final String name = authentication.getName();
        final String password = authentication.getCredentials().toString();
        if(usersMap.get(name) != null && name.length() != 0 && password.length() !=0)
        {
            UserData u = usersMap.get(name);
            return u.getPassword().equals(password);
        }
        else if(usersMap.get(name) == null && name.length() != 0 && password.length() !=0)
        {
            usersMap.put(name,new UserData(name,password,0,LocalTime.now(), AccountStatusinfo.Created));
            return true;
        }
        return false;
    }

}
