package service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import org.jooby.Jooby;
import org.jooby.apitool.ApiTool;
import org.jooby.json.Gzon;
import dao.SaleStorageDAO;
import resource.SaleResource;
import resource.SaleStorageResource;
import resource.CustomerSaleResource;
import resource.CustomerSaleSummaryResource;

public class Server extends Jooby {

    public Server(){
        
        SaleStorageDAO dao = new SaleStorageDAO();
        
        use(new Gzon());
        use(new SaleStorageResource(dao));
        use(new SaleResource(dao));
        use(new CustomerSaleResource(dao));
        use(new CustomerSaleSummaryResource(dao));
 
        use(new ApiTool().swagger(new ApiTool.Options("/swagger").use("SaleDirectory.yml")));
 
    }
    
    public static void main(String[] args){
        Server server = new Server();
        server.port(8080); // Specify the port
        
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
