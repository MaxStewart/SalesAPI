package resource;

import org.jooby.MediaType;
import org.jooby.Status;
import dao.SaleStorageDAO;
import domain.ErrorMessage;
import domain.Sale;
import org.jooby.Jooby;

public class SaleStorageResource extends Jooby {
    
    public SaleStorageResource(SaleStorageDAO dao) {
        
        path("/api/directory/", () ->{
            
            get(() -> {
                return dao.getSales();
            });
            
            post((req, rsp) -> {
                Sale sale = req.body(Sale.class);
                                
                if(!dao.exists(sale.getId())){
                    dao.addSales(sale);
                    
                    rsp.status(Status.CREATED).send(sale);
                } else {
                    rsp.status(Status.UNPROCESSABLE_ENTITY).send(new ErrorMessage("There is already a sale with that ID."));
                }
                
            });
            
        }).produces(MediaType.json).consumes(MediaType.json);
    }
}
