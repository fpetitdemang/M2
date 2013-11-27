package controllers;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import views.html.*;

import models.*;

/**
 * Manage a database of computers
 */
public class Application extends Controller {
    
    /**
     * This result directly redirect to application home.
     */
    public static Result GO_HOME = redirect(
        routes.Application.list(0, "name", "asc", "")
    );
    
    /**
     * Handle default path requests, redirect to computers list
     */
    public static Result index() {
        return GO_HOME;
    }

    /**
     * Display the paginated list of computers.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on computer names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                Computer.page(page, 10, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Computer.
     *
     * @param id Id of the computer to edit
     */
    public static Result edit(Long id) {
        Form<Computer> computerForm = form(Computer.class).fill(
            Computer.find.byId(id)
        );
        return ok(
            editForm.render(id, computerForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the computer to edit
     */
    public static Result update(Long id) {
        Form<Computer> computerForm = form(Computer.class).bindFromRequest();
        if(computerForm.hasErrors()) {
            return badRequest(editForm.render(id, computerForm));
        }
        computerForm.get().update(id);
        flash("success", "Computer " + computerForm.get().name + " has been updated");
        return GO_HOME;
    }
    
    /**
     * Display the 'new computer form'.
     */
    public static Result create() {
        Form<Computer> computerForm = form(Computer.class);
        return ok(
            createForm.render(computerForm)
        );
    }
    
    /**
     * Handle the 'new computer form' submission 
     */
    public static Result save() {
        Form<Computer> computerForm = form(Computer.class).bindFromRequest();
        if(computerForm.hasErrors()) {
            return badRequest(createForm.render(computerForm));
        }
        computerForm.get().save();              The same as -type unless the file is a symbolic link.  For symbolic links: if the -H or -P option was specified, true if the file  is
              a link to a file of type c; if the -L option has been given, true if c is `l'.  In other words, for symbolic links, -xtype checks the
              type of the file that -type does not check.

   ACTIONS
       -delete
              Delete files; true if removal succeeded.  If the removal failed, an error message is issued.  If -delete fails,  find's  exit  status
              will be nonzero (when it eventually exits).  Use of -delete automatically turns on the -depth option.

              Warnings:  Don't  forget  that  the  find  command line is evaluated as an expression, so putting -delete first will make find try to
              delete everything below the starting points you specified.  When testing a find command line  that  you  later  intend  to  use  with

        flash("success", "Computer " + computerForm.get().name + " has been created");
        return GO_HOME;
    }
    
    /**
     * Handle computer deletion
     */
    public static Result delete(Long id) {
        Computer.find.ref(id).delete();
        flash("success", "Computer has been deleted");
        return GO_HOME;
    }
    

}
            
