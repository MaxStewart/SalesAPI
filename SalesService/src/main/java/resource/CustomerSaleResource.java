package resource;

import dao.SaleStorageDAO;
import domain.ErrorMessage;
import org.jooby.Jooby;
import org.jooby.MediaType;
import org.jooby.Status;

public class CustomerSaleResource extends Jooby {
    
    public CustomerSaleResource(SaleStorageDAO dao){
        
        path("api/directory/sale/customer", () -> {

            use("/:id", (req, rsp, chain) -> {
                String id = req.param("id").value();

                if (dao.customerExists(id)) {
                    chain.next(req, rsp);
                } else {
                    rsp.status(Status.NOT_FOUND).send(new ErrorMessage("There is no customer with that account"));
                }
            });

            get("/:id", (req) -> {
                String id = req.param("id").value();
                return dao.getAllByCustomerId(id);
            });
        }).produces(MediaType.json).consumes(MediaType.json);
    }
}
