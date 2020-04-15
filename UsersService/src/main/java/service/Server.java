package service;

import dao.AccountStorageDAO;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import org.jooby.Jooby;
import org.jooby.apitool.ApiTool;
import org.jooby.json.Gzon;
import resource.AccountStorageResource;
import resource.UserAccountsResource;

public class Server extends Jooby {

    public Server(){
        
        AccountStorageDAO dao = new AccountStorageDAO();
        
        use(new Gzon());
        use(new AccountStorageResource(dao));
        use(new UserAccountsResource(dao));
 
        use(new ApiTool().swagger(new ApiTool.Options("/swagger").use("Directory.yml")));
 
    }
    
    public static void main(String[] args){
        Server server = new Server();
        server.port(8081); // Specify the port
        
        CompletableFuture.runAsync(() -> {
           server.start(); 
        });
        
        server.onStarted(() -> {
           System.out.println("\nServer Ready");
        });
        
        try{
            System.in.read();
            System.exit(0);
        } 
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
