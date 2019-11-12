package org.wahlzeit.testEnvironmentProvider;

import org.junit.rules.ExternalResource;
import org.wahlzeit.services.SysConfig;
import org.wahlzeit.webparts.WebPartTemplateService;

/**
 * A test setup class.
 *
 * @review
 */
public class SysConfigProvider extends ExternalResource
{

    @Override
    protected void before() throws Throwable
    {
        SysConfig sysConfig = new SysConfig("src/main/webapp");
        WebPartTemplateService webPartTemplateService = new WebPartTemplateService();
        webPartTemplateService.setTemplatesDir(sysConfig.getTemplatesDir());
    }

    @Override
    protected void after()
    {
    }
}
