package resource;

import dao.AccountStorageDAO;
import domain.ErrorMessage;
import domain.UserAccount;
import org.jooby.Jooby;
import org.jooby.MediaType;
import org.jooby.Status;

public class AccountStorageResource extends Jooby {

    public AccountStorageResource(AccountStorageDAO dao) {
        
        path("/api/directory/", () ->{
            
            get(() -> {
                return dao.getUserAccounts();
            });
            
            post((req, rsp) -> {
                UserAccount userAccount = req.body(UserAccount.class);
                
                String uri = "http://" + req.hostname() + ":" + req.port() + "" + req.path() + "/user/" + userAccount.getId();
                
                userAccount.setUri(uri);
                
                if(!dao.exists(userAccount.getId())){
                    dao.addUserAccount(userAccount);
                    
                    rsp.status(Status.CREATED).send(userAccount);
                } else {
                    rsp.status(Status.UNPROCESSABLE_ENTITY).send(new ErrorMessage("There is already an Account with that ID."));
                }
                
            });
            
        }).produces(MediaType.json).consumes(MediaType.json);
    }
    
    
    
}
