package resource;

import dao.AccountStorageDAO;
import domain.ErrorMessage;
import domain.UserAccount;
import org.jooby.Jooby;
import org.jooby.MediaType;
import org.jooby.Status;

public class UserAccountsResource extends Jooby {
    
    public UserAccountsResource(AccountStorageDAO dao){
        
        path("api/directory/user", () -> {
            
            use("/:id", (req, rsp, chain) -> {
                String id = req.param("id").value();
                
                if(dao.exists(id)){
                    chain.next(req, rsp);
                } else {
                    rsp.status(Status.NOT_FOUND).send(new ErrorMessage("There is no account matching that ID."));
                }
            });
            
            get("/:id", (req) -> {
                String id = req.param("id").value();
                return dao.getById(id);
            });
            
            delete("/:id", (req, rsp) -> {
                String id = req.param("id").value();
                dao.delete(id);
                rsp.status(Status.NO_CONTENT);
            });
            
            put("/:id", (req, rsp) -> {
                String id = req.param("id").value();
                UserAccount account = req.body().to(UserAccount.class);
                
                if(!id.equals(account.getId())){
                    rsp.status(Status.UNPROCESSABLE_ENTITY).send(new ErrorMessage("User Account ID cannot be modified via this opperation."));
                } else {
                    dao.updateItem(id, account);
                    rsp.status(Status.NO_CONTENT);
                }
            });
        }).produces(MediaType.json).consumes(MediaType.json);
    }
}
