package resource;

import org.jooby.Jooby;
import dao.SaleStorageDAO;
import domain.ErrorMessage;
import org.jooby.MediaType;
import org.jooby.Status;
import domain.Sale;

public class SaleResource extends Jooby {

    public SaleResource(SaleStorageDAO dao) {

        path("api/directory/sale", () -> {

            use("/:id", (req, rsp, chain) -> {
                String id = req.param("id").value();

                if (dao.exists(id)) {
                    chain.next(req, rsp);
                } else {
                    rsp.status(Status.NOT_FOUND).send(new ErrorMessage("There is no account matching that ID."));
                }
            });

            
            delete("/:id", (req, rsp) -> {
                String id = req.param("id").value();
                dao.delete(id);
                rsp.status(Status.NO_CONTENT);
            });

            put("/:id", (req, rsp) -> {
                String id = req.param("id").value();
                Sale sale = req.body().to(Sale.class);

                if (!id.equals(sale.getId())) {
                    rsp.status(Status.UNPROCESSABLE_ENTITY).send(new ErrorMessage("Sale cannot be modified via this opperation."));
                } else {
                    dao.updateItem(id, sale);
                    rsp.status(Status.NO_CONTENT);
                }
            });
        }).produces(MediaType.json).consumes(MediaType.json);
    }
}
