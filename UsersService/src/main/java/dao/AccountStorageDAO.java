package dao;

import domain.UserAccount;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AccountStorageDAO {
    
    private static final Map<String, UserAccount> items = new TreeMap<>();
    
    static {
        if(items.isEmpty()){
            items.put("12345", new UserAccount("12345", "test@email.com", "testUser", "Test", "User", "Newbies", "http://localhost:8081/api/directory/user/12345"));
            items.put("45678", new UserAccount("45678", "stew@email.com", "stewart1", "Max", "Stewart", "Newbies", "http://localhost:8081/api/directory/user/45678"));
        }
    }
    
    public List<UserAccount> getUserAccounts(){
        return new ArrayList<>(items.values());
    }
    
    public void addUserAccount(UserAccount item){
        items.put(item.getId(), item);
    }
    
    public UserAccount getById(String id){
        return items.get(id);
    }
    
    public void delete(String id){
        items.remove(id);
    }
    
    public void updateItem(String id, UserAccount updatedAccount) {
        items.put(id, updatedAccount);
    }
    
    public boolean exists(String id){
        return items.containsKey(id);
    }
}
