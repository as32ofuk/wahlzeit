package org.wahlzeit.handlers;

import org.wahlzeit.handlers.forms.*;
import org.wahlzeit.handlers.pages.*;
import org.wahlzeit.model.AccessRights;
import org.wahlzeit.model.PhotoCaseManager;
import org.wahlzeit.model.PhotoManager;
import org.wahlzeit.model.UserManager;
import org.wahlzeit.services.SysConfig;

public class HandlerFactory
{
    protected PhotoManager photoManager;
    protected UserManager userManager;
    protected SysConfig sysConfig;

    public HandlerFactory(PhotoManager photoManager, UserManager userManager, SysConfig sysConfig)
    {
        this.photoManager = photoManager;
        this.userManager = userManager;
        this.sysConfig = sysConfig;
    }

    public NullFormHandler createNullFormHandler()
    {
        return new NullFormHandler(photoManager, userManager, sysConfig);
    }

    public ShowNotePageHandler createShowNotePageHandler()
    {
        return new ShowNotePageHandler(photoManager, userManager, sysConfig);
    }

    public FilterPhotosFormHandler createFilterPhotosFormHandler()
    {
        return new FilterPhotosFormHandler(photoManager, userManager, sysConfig);
    }

    public PraisePhotoFormHandler createPraisePhotoFormHandler()
    {
        return new PraisePhotoFormHandler(photoManager, userManager, sysConfig);
    }

    public ShowPhotoPageHandler createShowPhotoPageHandler()
    {
        return new ShowPhotoPageHandler(photoManager, userManager, sysConfig);
    }

    public FilterPhotosPageHandler createFilterPhotosPageHandler()
    {
        return new FilterPhotosPageHandler(photoManager, userManager, sysConfig);
    }

    public ResetSessionPageHandler createResetSessionPageHandler()
    {
        return new ResetSessionPageHandler(photoManager, userManager, sysConfig);
    }

    public ShowInfoPageHandler createShowInfoPageHandler(AccessRights accessRights, String infoTemplateName)
    {
        return new ShowInfoPageHandler(photoManager, userManager, sysConfig, accessRights, infoTemplateName);
    }

    public FlagPhotoFormHandler createFlagPhotoFormHandler(PhotoCaseManager photoCaseManager)
    {
        return new FlagPhotoFormHandler(photoManager, userManager, sysConfig, photoCaseManager);
    }

    public ShowPartPageHandler createShowPartPageHandler(AccessRights accessRights, WebPartHandler webPartHandler)
    {
        return new ShowPartPageHandler(photoManager, userManager, sysConfig, accessRights, webPartHandler);
    }

    public SendEmailFormHandler createSendEmailFormHandler()
    {
        return new SendEmailFormHandler(photoManager, userManager, sysConfig);
    }

    public TellFriendFormHandler createTellFriendFormHandler()
    {
        return new TellFriendFormHandler(photoManager, userManager, sysConfig);
    }

    public SetOptionsFormHandler createSetOptionsFormHandler()
    {
        return new SetOptionsFormHandler(photoManager, userManager, sysConfig);
    }

    public LoginFormHandler createLoginFormHandler()
    {
        return new LoginFormHandler(photoManager, userManager, sysConfig);
    }

    public LogoutPageHandler createLogoutPageHandler()
    {
        return new LogoutPageHandler(photoManager, userManager, sysConfig);
    }

    public SetLanguagePageHandler createSetLanguagePageHandler()
    {
        return new SetLanguagePageHandler(photoManager, userManager, sysConfig);
    }

    public SetPhotoSizePageHandler createSetPhotoSizePageHandler()
    {
        return new SetPhotoSizePageHandler(photoManager, userManager, sysConfig);
    }

    public ShowUserProfileFormHandler createShowUserProfileFormHandler()
    {
        return new ShowUserProfileFormHandler(photoManager, userManager, sysConfig);
    }

    public ShowUserPhotoFormHandler createShowUserPhotoFormHandler()
    {
        return new ShowUserPhotoFormHandler(photoManager, userManager, sysConfig);
    }

    public ShowUserHomePageHandler createShowUserHomePageHandler()
    {
        return new ShowUserHomePageHandler(photoManager, userManager, sysConfig);
    }

    public EditUserProfileFormHandler createEditUserProfileFormHandler()
    {
        return new EditUserProfileFormHandler(photoManager, userManager, sysConfig);
    }

    public EditUserPhotoFormHandler createEditUserPhotoFormHandler()
    {
        return new EditUserPhotoFormHandler(photoManager, userManager, sysConfig);
    }

    public UploadPhotoFormHandler createUploadPhotoFormHandler()
    {
        return new UploadPhotoFormHandler(photoManager, userManager, sysConfig);
    }

    public EditPhotoCaseFormHandler createEditPhotoCaseFormHandler(PhotoCaseManager photoCaseManager)
    {
        return new EditPhotoCaseFormHandler(photoManager, userManager, sysConfig, photoCaseManager);
    }

    public ShowPhotoCasesPageHandler createShowPhotoCasesPageHandler(PhotoCaseManager photoCaseManager)
    {
        return new ShowPhotoCasesPageHandler(photoManager, userManager, sysConfig, photoCaseManager);
    }

    public ShowAdminPageHandler createShowAdminPageHandler()
    {
        return new ShowAdminPageHandler(photoManager, userManager, sysConfig);
    }

    public AdminUserProfileFormHandler createAdminUserProfileFormHandler()
    {
        return new AdminUserProfileFormHandler(photoManager, userManager, sysConfig);
    }

    public AdminUserPhotoFormHandler createAdminUserPhotoFormHandler()
    {
        return new AdminUserPhotoFormHandler(photoManager, userManager, sysConfig);
    }
}
