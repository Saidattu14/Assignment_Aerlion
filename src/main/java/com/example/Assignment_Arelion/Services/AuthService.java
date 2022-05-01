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
    public boolean RegisterAuthentication(final Authentication authentication)
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

    public boolean RateLimitingRequests(final Authentication authentication)
    {
        final String name = authentication.getName();
        if(usersMap.get(name) != null)
        {
            UserData u = usersMap.get(name);
            String lastrequesttimeStrArr [] = u.getLastrequesttime().toString().split(":");
            String currentrequesttimeStr[] = LocalTime.now().toString().split(":");
            String lastrequesttime = "";
            String currentrequesttime = "";
            for(int i = 0; i<currentrequesttimeStr.length;i++)
            {
                if(i != 2) {
                    currentrequesttime = currentrequesttime + currentrequesttimeStr[i];
                }
                else
                {
                    String seconds[] = currentrequesttimeStr[i].split("[.]");
                    currentrequesttime = currentrequesttime + seconds[0];

                }
            }
            for(int i = 0; i<lastrequesttimeStrArr.length;i++)
            {
                if(i != 2) {
                    lastrequesttime = lastrequesttime +lastrequesttimeStrArr[i];
                }
                else
                {
                    String seconds[] = lastrequesttimeStrArr[i].split("[.]");
                    lastrequesttime = lastrequesttime + seconds[0];
                }
            }
            int lastrequesttimedata = Integer.parseInt(lastrequesttime);
            int currentrequesttimedata = Integer.parseInt(currentrequesttime);
//            System.out.println(currentrequesttimedata);
//            System.out.println(lastrequesttimedata);
            if(lastrequesttimedata > currentrequesttimedata)
            {
                currentrequesttimedata = lastrequesttimedata + currentrequesttimedata;
            }
            if(currentrequesttimedata - lastrequesttimedata >= 60)
            {
                u.setLastrequesttime(LocalTime.now());
                u.setNoofrequestsperminute(1);
            }
            else if(currentrequesttimedata - lastrequesttimedata < 60)
            {
                if(u.getNoofrequestsperminute() <=5)
                {
                    u.setNoofrequestsperminute(u.getNoofrequestsperminute()+1);
                }
                else {
                    return false;
                }
            }

//            Arrays.stream(lastrequesttimeStrArr).forEach(x->lastrequesttime.concat(x));
//            Arrays.stream(currentrequesttimeStr).forEach(x->currentrequesttime.concat(x));
//            System.out.println(lastrequesttime);
//            System.out.println(currentrequesttime);
        }
        return true;
    }
    /**
     * This method validates the User Account data when user tries to access the API data.
     * Main Purpose is to activate the UserData when the user SignIn
     * @param authentication
     * @return true if the user has a registed Account Else it returns false.
     */
    public boolean LoginAuthentication(final Authentication authentication)
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
    public boolean UserDataAuthentication(final Authentication authentication)
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
