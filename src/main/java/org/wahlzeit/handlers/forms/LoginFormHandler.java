package org.wahlzeit.handlers.forms;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import org.wahlzeit.handlers.PartUtil;
import org.wahlzeit.handlers.WebPartHandlerManager;
import org.wahlzeit.model.*;
import org.wahlzeit.model.clients.Administrator;
import org.wahlzeit.model.clients.Client;
import org.wahlzeit.model.clients.User;
import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.services.SysConfig;
import org.wahlzeit.webparts.WebPart;
import org.wahlzeit.webparts.WebPartTemplateService;

import java.util.Map;
import java.util.logging.Logger;

/**
 * When a user signs in with its Google account, this handler assures that a Wahlzeit user exists for the Google user.
 * If not {@link LoginFormHandler} creates one.
 */
public class LoginFormHandler extends AbstractWebFormHandler
{
    private static final Logger log = Logger.getLogger(LoginFormHandler.class.getName());

    public LoginFormHandler(PhotoManager photoManager, UserManager userManager, SysConfig sysConfig, WebPartHandlerManager webPartHandlerManager, WebPartTemplateService webPartTemplateService)
    {
        super(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService, PartUtil.LOGIN_FORM_FILE, AccessRights.GUEST);
    }

    @Override
    protected void doMakeWebPart(UserSession us, WebPart part)
    {
        // do nothing as there is no page that should be displayed
        log.info("doMakeWebPart");
    }

    /**
     * Called when a new user logged in. Make sure that a Wahlzeit user exist.
     */
    @Override
    protected String doHandleGet(UserSession us, String link, Map args)
    {
        log.info("Link: " + link);

        UserService userService = UserServiceFactory.getUserService();
        com.google.appengine.api.users.User googleUser = userService.getCurrentUser();

        if(googleUser != null)
        {
            // googleUser logged in
            log.config(LogBuilder.createSystemMessage().
                    addMessage("Google user exists").
                    addParameter("E-Mail", googleUser.getEmail()).toString());
            String userId = googleUser.getUserId();
            User user = userManager.getUserById(userId);
            if(user != null)
            {
                // Wahlzeit user already exists
                us.setClient(user);
                log.config(LogBuilder.createSystemMessage().
                        addMessage("Wahlzeit user exists").
                        addParameter("id", user.getId()).toString());
            }
            else
            {
                // create new Wahlzeit user
                String emailAddress = googleUser.getEmail();
                String nickName = googleUser.getNickname();

                Client previousClient = us.getClient();
                if(userService.isUserAdmin())
                {
                    user = new Administrator(userManager, userId, nickName, emailAddress, previousClient);
                }
                else
                {
                    user = new User(userManager, userId, nickName, emailAddress, previousClient);
                }
                userManager.emailWelcomeMessage(us, user);
                us.setClient(user);

                log.info(LogBuilder.createUserMessage().addAction("Signup").toString());
            }

//              TODO
//            if (user.getStatus().isDisabled()) {
//                us.setMessage(us.getConfiguration().getUserIsDisabled());
//                return PartUtil.LOGIN_PAGE_NAME;
//            }

            return PartUtil.SHOW_USER_HOME_PAGE_NAME;
        }
        else
        {
            // googleUser not logged in
            return PartUtil.SHOW_NOTE_PAGE_NAME;
        }
    }

}
