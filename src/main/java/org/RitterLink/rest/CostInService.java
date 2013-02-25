package org.RitterLink.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.RitterLink.model.Account;
import org.RitterLink.model.Expenditure;
import org.RitterLink.model.Member;
import org.RitterLink.model.SubAccount;



@Path("/costs")
@RequestScoped
@Stateful
public class CostInService {
	
	@Inject
	private Logger log;
	
	@Inject
	private EntityManager em;

	@Inject
	private Event<Expenditure> expenditureEventSrc;

	@Inject
	private Validator validator;
	
	@Inject
	private Expenditure expenditure;
	
	@Inject
	private SubAccount subAccount;
	
	@Inject
	private Account account;

    @POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)

	

	public Response createExpenditure(@FormParam("select-subAccount") String subaccount_id, @FormParam("checkbox-realm") String realm, @FormParam("datum") String datum, @FormParam("description") String description, @FormParam("soll") String soll){

    	Response.ResponseBuilder builder = null;
    	subAccount = em.find(SubAccount.class, Integer.parseInt(subaccount_id));
    	expenditure.setSubAccount(subAccount);
    	expenditure.setDatum(datum);

    	expenditure.setRealm(realm);
    	expenditure.setDescription(description);
    	expenditure.setSoll(soll);
    	expenditure.setHaben("0,00");
    	try {
             //Register the member
             log.info("Registering " + expenditure.getSubAccount().getLabel());
             em.persist(expenditure);

             //Trigger the creation event
             expenditureEventSrc.fire(expenditure);

             //Create an "ok" response
             builder = Response.ok();
          } catch (ConstraintViolationException ce) {
             //Handle bean validation issues
             builder = createViolationResponse(ce.getConstraintViolations());
          } catch (ValidationException e) {
             //Handle the unique constrain violation
             Map<String, String> responseObj = new HashMap<String, String>();
             responseObj.put("email","Email taken");
             builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
          }

          return builder.build();
		
	}
    
    /**
     * Creates a JAX-RS "Bad Request" response including a map of all violation fields, and their message.
     * This can then be used by clients to show violations.
     *
     * @param violations A set of violations that needs to be reported
     * @return JAX-RS response containing all violations
     */
    private Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
       log.fine("Validation completed. violations found: " + violations.size());

       Response.ResponseBuilder builder = null;
       Map<String, String> responseObj = new HashMap<String, String>();

       for (ConstraintViolation<?> violation : violations) {
          responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
       }

       return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
    }
    
    @GET
    @Path("/expenditures")
    @Produces("text/xml; charset=UTF-8")
    public List<Expenditure> listAllExpenditures() {
       final ArrayList<Expenditure> results = (ArrayList<Expenditure>) em.createQuery("SELECT e FROM Expenditure e", Expenditure.class).getResultList();
       return results;
    }
    
    @GET
    @Path("/accounts")
    @Produces("text/xml; charset=UTF-8")
    public List<Account> listAllAccounts() {
       final ArrayList<Account> results = (ArrayList<Account>) em.createQuery("SELECT a FROM Account a", Account.class).getResultList();
       return results;
    }
    
    @GET
    @Path("/accounts/subaccounts/{id:[0-9][0-9]*}")
    @Produces("text/xml; charset=UTF-8")
    public List<SubAccount> lookupSubAccountsByAccountIdJSON(@PathParam("id") int id) {
       account= em.find(Account.class, id);
       List<SubAccount> result= account.getSubAccounts();
       return result;
    }


}
