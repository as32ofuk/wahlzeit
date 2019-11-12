package org.wahlzeit.testEnvironmentProvider;

import org.junit.rules.ExternalResource;
import org.wahlzeit.handlers.PartUtil;
import org.wahlzeit.handlers.forms.TellFriendFormHandler;
import org.wahlzeit.handlers.forms.WebFormHandler;
import org.wahlzeit.handlers.WebPartHandlerManager;
import org.wahlzeit.model.GlobalsManager;
import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PhotoManager;
import org.wahlzeit.model.UserManager;
import org.wahlzeit.model.persistence.DatastoreAdapter;
import org.wahlzeit.model.persistence.ImageStorage;
import org.wahlzeit.services.SysConfig;
import org.wahlzeit.webparts.WebPartTemplateService;

/**
 * A test setup class.
 *
 * @review
 */
public class WebFormHandlerProvider extends ExternalResource
{

    private WebFormHandler webFormHandler;

    @Override
    protected void before() throws Throwable
    {
        UserManager userManager = new UserManager();
        GlobalsManager globalsManager = new GlobalsManager(userManager);
        PhotoFactory photoFactory = new PhotoFactory(userManager);
        ImageStorage imageStorage = new DatastoreAdapter();
        PhotoManager photoManager = new PhotoManager(photoFactory, userManager, globalsManager, imageStorage);
        SysConfig sysConfig = new SysConfig("src/main/webapp");

        WebPartTemplateService webPartTemplateService = new WebPartTemplateService();

        WebPartHandlerManager webPartHandlerManager = new WebPartHandlerManager();
        webPartHandlerManager.addWebPartHandler(PartUtil.TELL_FRIEND_FORM_NAME, new TellFriendFormHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService));
        webFormHandler = webPartHandlerManager.getWebFormHandler(PartUtil.TELL_FRIEND_FORM_NAME);
    }

    public WebFormHandler getWebFormHandler()
    {
        return webFormHandler;
    }
}
