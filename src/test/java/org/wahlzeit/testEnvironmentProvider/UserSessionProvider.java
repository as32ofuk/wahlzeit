package org.wahlzeit.testEnvironmentProvider;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;
import org.junit.rules.ExternalResource;
import org.wahlzeit.model.*;
import org.wahlzeit.model.clients.Guest;
import org.wahlzeit.model.languages.EnglishModelConfig;
import org.wahlzeit.model.languages.GermanModelConfig;
import org.wahlzeit.model.languages.LanguageConfigs;
import org.wahlzeit.model.persistence.DatastoreAdapter;
import org.wahlzeit.model.persistence.ImageStorage;
import org.wahlzeit.services.Language;
import org.wahlzeit.services.SessionManager;
import org.wahlzeit.services.SysConfig;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Rule that provides a <code>UserSession</code> in the <code>SessionManager</code>
 */
public class UserSessionProvider extends ExternalResource
{

    public static final String USER_SESSION_NAME = "testContext";

    @Override
    protected void before() throws Throwable
    {
        UserManager userManager = new UserManager();
        GlobalsManager globalsManager = new GlobalsManager(userManager);
        PhotoFactory photoFactory = new PhotoFactory(userManager);
        ImageStorage imageStorage = new DatastoreAdapter();
        PhotoManager photoManager = new PhotoManager(photoFactory, userManager, globalsManager, imageStorage);

        SysConfig sysConfig = new SysConfig("src/main/webapp");
        // init language configs because they are used e.g. for AbstractWebPartHandler
        LanguageConfigs.put(Language.ENGLISH, new EnglishModelConfig(sysConfig));
        LanguageConfigs.put(Language.GERMAN, new GermanModelConfig(sysConfig));

        HttpSession httpSession = mock(HttpSession.class);
        when(httpSession.getAttribute(UserSession.INITIALIZED)).thenReturn(UserSession.INITIALIZED);
        String guestName = ObjectifyService.run(new Work<String>()
        {
            @Override
            public String run()
            {
                Guest guest = new Guest(photoManager, userManager);
                guest.setLanguage(Language.ENGLISH);
                return guest.getId();
            }
        });
        when(httpSession.getAttribute(UserSession.CLIENT_ID)).thenReturn(guestName);

        Map<String, Object> dummyMap = new HashMap<String, Object>();
        dummyMap.put(UserSession.MESSAGE, "dummy Message");
        when(httpSession.getAttribute(UserSession.SAVED_ARGS)).thenReturn(dummyMap);

        UserSession userSession = new UserSession(photoManager, userManager, USER_SESSION_NAME, "", httpSession, "en");
        SessionManager.setThreadLocalSession(userSession);
    }

    @Override
    protected void after()
    {
        SessionManager.setThreadLocalSession(null);
    }

}
