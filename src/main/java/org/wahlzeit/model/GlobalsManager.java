package org.wahlzeit.model;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;
import org.wahlzeit.services.ObjectManager;
import org.wahlzeit.servlets.AbstractServlet;

import java.util.logging.Logger;

import static org.wahlzeit.services.OfyService.ofy;

/**
 * Manager that cares about the global variables. It is used from the outside by the following two methods:
 *
 * @review
 * @see #loadGlobals()
 * @see #saveGlobals()
 */
public class GlobalsManager extends ObjectManager
{

    private static final Logger log = Logger.getLogger(GlobalsManager.class.getName());

    protected UserManager userManager;

    public GlobalsManager(UserManager userManager)
    {
        this.userManager = userManager;
    }

    /**
     * @methodtype command Loads all global variables and stores them in their corresponding classes.
     */
    public void loadGlobals()
    {
        initGlobals();
        Globals globals = ObjectifyService.run(() -> readObject(Globals.class, Globals.DEAULT_ID));
        log.info(globals.asString());

        userManager.setLastClientId(globals.getLastUserId());
        PhotoId.setCurrentIdFromInt(globals.getLastPhotoId());
        Case.setLastCaseId(new CaseId(globals.getLastCaseId()));
        AbstractServlet.setLastSessionId(globals.getLastSessionId());
    }

    /**
     * @methodtype wrapper
     */
    private void initGlobals()
    {
        if(!hasGlobals())
        {
            createDefaultGlobals();
        }
    }

    /**
     * @methodtype boolean querry
     */
    private boolean hasGlobals()
    {
        boolean hasGlobals = ObjectifyService.run(() -> ofy().load().type(Globals.class).first().now() != null);
        System.out.println("hasGlobals: " + hasGlobals);
        return hasGlobals;
    }

    /**
     * @methodtype command
     */
    private void createDefaultGlobals()
    {
        ObjectifyService.run((Work<Boolean>) () ->
        {
            Globals globals = new Globals();
            globals.setLastUserId(Globals.DEAULT_ID);
            globals.setLastPhotoId(0);
            globals.setLastCaseId(0);
            globals.setLastSessionId(0);
            ofy().save().entity(globals).now();
            return null;
        });
        System.out.println("created default Globals");
    }

    /**
     * @methodtype command Saves all global variables.
     */
    public synchronized void saveGlobals()
    {
        final Globals globals = new Globals();
        globals.setLastUserId(userManager.getLastClientId());
        globals.setLastPhotoId(PhotoId.getCurrentIdAsInt());
        globals.setLastCaseId(Case.getLastCaseId().asInt());
        globals.setLastSessionId(AbstractServlet.getLastSessionId());
        log.info(globals.asString());

        ObjectifyService.run(new Work<Void>()
        {
            @Override
            public Void run()
            {
                writeObject(globals);
                return null;
            }
        });
    }
}
