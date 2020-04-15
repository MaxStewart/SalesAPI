/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import domain.ErrorMessage;
import org.jooby.Status;
import org.jooby.MediaType;
import dao.SaleStorageDAO;
import org.jooby.Jooby;

/**
 *
 * @author maxst
 */
public class CustomerSaleSummaryResource extends Jooby {

    public CustomerSaleSummaryResource(SaleStorageDAO dao) {

        path("/api/directory/sale/customer", () -> {
            
            use("/:id", (req, rsp, chain) -> {
                String id = req.param("id").value();

                if (dao.customerExists(id)) {
                    chain.next(req, rsp);
                } else {
                    rsp.status(Status.NOT_FOUND).send(new ErrorMessage("There is no customer with that ID"));
                }
            });

            get("/:id/summary", (req) -> {
                String id = req.param("id").value();
                return dao.getSummary(id);
            });
            
        }).produces(MediaType.json).consumes(MediaType.json);
    }
}
