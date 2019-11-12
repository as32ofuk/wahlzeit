package org.wahlzeit.handlers;

import org.wahlzeit.handlers.forms.*;
import org.wahlzeit.handlers.pages.*;
import org.wahlzeit.model.AccessRights;
import org.wahlzeit.model.PhotoCaseManager;
import org.wahlzeit.model.PhotoManager;
import org.wahlzeit.model.UserManager;
import org.wahlzeit.services.SysConfig;
import org.wahlzeit.webparts.WebPartTemplateService;

public class HandlerFactory
{
    protected PhotoManager photoManager;
    protected UserManager userManager;
    protected SysConfig sysConfig;
    protected WebPartHandlerManager webPartHandlerManager;
    protected WebPartTemplateService webPartTemplateService;

    public HandlerFactory(PhotoManager photoManager, UserManager userManager, SysConfig sysConfig, WebPartHandlerManager webPartHandlerManager, WebPartTemplateService webPartTemplateService)
    {
        this.photoManager = photoManager;
        this.userManager = userManager;
        this.sysConfig = sysConfig;
        this.webPartHandlerManager = webPartHandlerManager;
        this.webPartTemplateService = webPartTemplateService;
    }

    public NullFormHandler createNullFormHandler()
    {
        return new NullFormHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService);
    }

    public ShowNotePageHandler createShowNotePageHandler()
    {
        return new ShowNotePageHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService);
    }

    public FilterPhotosFormHandler createFilterPhotosFormHandler()
    {
        return new FilterPhotosFormHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService);
    }

    public PraisePhotoFormHandler createPraisePhotoFormHandler()
    {
        return new PraisePhotoFormHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService);
    }

    public ShowPhotoPageHandler createShowPhotoPageHandler()
    {
        return new ShowPhotoPageHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService);
    }

    public FilterPhotosPageHandler createFilterPhotosPageHandler()
    {
        return new FilterPhotosPageHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService);
    }

    public ResetSessionPageHandler createResetSessionPageHandler()
    {
        return new ResetSessionPageHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService);
    }

    public ShowInfoPageHandler createShowInfoPageHandler(AccessRights accessRights, String infoTemplateName)
    {
        return new ShowInfoPageHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService, accessRights, infoTemplateName);
    }

    public FlagPhotoFormHandler createFlagPhotoFormHandler(PhotoCaseManager photoCaseManager)
    {
        return new FlagPhotoFormHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService, photoCaseManager);
    }

    public ShowPartPageHandler createShowPartPageHandler(AccessRights accessRights, WebPartHandler webPartHandler)
    {
        return new ShowPartPageHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService, accessRights, webPartHandler);
    }

    public SendEmailFormHandler createSendEmailFormHandler()
    {
        return new SendEmailFormHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService);
    }

    public TellFriendFormHandler createTellFriendFormHandler()
    {
        return new TellFriendFormHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService);
    }

    public SetOptionsFormHandler createSetOptionsFormHandler()
    {
        return new SetOptionsFormHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService);
    }

    public LoginFormHandler createLoginFormHandler()
    {
        return new LoginFormHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService);
    }

    public LogoutPageHandler createLogoutPageHandler()
    {
        return new LogoutPageHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService);
    }

    public SetLanguagePageHandler createSetLanguagePageHandler()
    {
        return new SetLanguagePageHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService);
    }

    public SetPhotoSizePageHandler createSetPhotoSizePageHandler()
    {
        return new SetPhotoSizePageHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService);
    }

    public ShowUserProfileFormHandler createShowUserProfileFormHandler()
    {
        return new ShowUserProfileFormHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService);
    }

    public ShowUserPhotoFormHandler createShowUserPhotoFormHandler()
    {
        return new ShowUserPhotoFormHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService);
    }

    public ShowUserHomePageHandler createShowUserHomePageHandler()
    {
        return new ShowUserHomePageHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService);
    }

    public EditUserProfileFormHandler createEditUserProfileFormHandler()
    {
        return new EditUserProfileFormHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService);
    }

    public EditUserPhotoFormHandler createEditUserPhotoFormHandler()
    {
        return new EditUserPhotoFormHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService);
    }

    public UploadPhotoFormHandler createUploadPhotoFormHandler()
    {
        return new UploadPhotoFormHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService);
    }

    public EditPhotoCaseFormHandler createEditPhotoCaseFormHandler(PhotoCaseManager photoCaseManager)
    {
        return new EditPhotoCaseFormHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService, photoCaseManager);
    }

    public ShowPhotoCasesPageHandler createShowPhotoCasesPageHandler(PhotoCaseManager photoCaseManager)
    {
        return new ShowPhotoCasesPageHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService, photoCaseManager);
    }

    public ShowAdminPageHandler createShowAdminPageHandler()
    {
        return new ShowAdminPageHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService);
    }

    public AdminUserProfileFormHandler createAdminUserProfileFormHandler()
    {
        return new AdminUserProfileFormHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService);
    }

    public AdminUserPhotoFormHandler createAdminUserPhotoFormHandler()
    {
        return new AdminUserPhotoFormHandler(photoManager, userManager, sysConfig, webPartHandlerManager, webPartTemplateService);
    }
}
