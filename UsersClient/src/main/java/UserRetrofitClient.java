
import api.AccountApi;
import api.DirectoryApi;
import domain.Account;
import java.io.IOException;
import java.util.List;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRetrofitClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8081/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        
        DirectoryApi directoryApi = retrofit.create(DirectoryApi.class);
        
        // Create a new user account
        Account account = new Account();
        account.setId("334432");
        account.setEmail("newUser@email.com");
        account.setFirstName("New");
        account.setLastName("User");
        account.setUsername("newUser");
        account.setGroup("Newbies");
        account.setUri("http://localhost:8081/api/directory/user/334432");
        // POST new account
        directoryApi.createNewUserAccount(account).execute().body();
        
        // GET all accounts
        List<Account> accounts = directoryApi.getAllTheAccounts().execute().body();
        System.out.println(accounts);
        
        // DELETE user account
        AccountApi accountApi = retrofit.create(AccountApi.class);
        accountApi.deleteAccount("334432").execute().body();
        accounts.clear();
        
        // GET user accounts to show it worked
        accounts = directoryApi.getAllTheAccounts().execute().body();
        System.out.println(accounts);
        
        // PUT: update a existing user
        Account updateAccount = new Account();
        updateAccount.setId("12345");
        updateAccount.setEmail("updated@email.com");
        updateAccount.setFirstName("Updated");
        updateAccount.setLastName("User");
        updateAccount.setUsername("newUser");
        updateAccount.setGroup("Newbies");
        updateAccount.setUri("http://localhost:8081/api/directory/user/12345");
        accountApi.updateAccount(updateAccount, "12345") .execute().body();
        
        // GET all accounts to show it worked
        accounts.clear();
        accounts = directoryApi.getAllTheAccounts().execute().body();
        System.out.println(accounts);
                        
    }
    
}
