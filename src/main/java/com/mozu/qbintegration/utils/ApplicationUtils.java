package com.mozu.qbintegration.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mozu.api.ApiContext;
import com.mozu.api.ApiError;
import com.mozu.api.ApiException;
import com.mozu.api.contracts.sitesettings.application.Application;
import com.mozu.api.contracts.sitesettings.application.Capability;
import com.mozu.api.resources.commerce.settings.ApplicationResource;
import com.mozu.api.security.AppAuthenticator;

public class ApplicationUtils {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationUtils.class);
    
    public static String getAppNamespace(){
        String appId = AppAuthenticator.getInstance().getAppAuthInfo().getApplicationId();
        return appId.substring(0, appId.indexOf('.'));
    }
    
    public static final void setApplicationToInitialized(ApiContext apiContext) throws Exception {
        String errorMsg = null;
        
        // get the application information
        ApplicationResource appResource = new ApplicationResource(apiContext);
        
        Application application;
        try {
            application = appResource.thirdPartyGetApplication();
            logger.debug("Application retrieved");
        } catch (Exception e) {
            errorMsg = "Exception getting application information: ";
            if (e instanceof ApiException) {
                ApiError apiError = ((ApiException)e).getApiError();
                if (apiError!=null) {
                    errorMsg = errorMsg +
                          " App name: " + apiError.getApplicationName() +
                          " Correlation ID: " + apiError.getCorrelationId() + 
                          " Error Code " + apiError.getErrorCode() + 
                          " Message: " + apiError.getMessage();
                } else {
                    errorMsg = errorMsg.concat(" ").concat(e.getMessage());
                }
            } else {
                errorMsg = errorMsg.concat(" ").concat(e.getMessage());
            }
            logger.debug(errorMsg);
            throw new Exception(errorMsg);
        }
        
        // Set the app to initialized
        application.setInitialized(true);
        
        try {
            appResource.thirdPartyUpdateApplication(application);
            logger.debug("Application updated");
        } catch (Exception e) {
            logger.warn("Exception updating application: " + e.getMessage());
            throw new Exception("Exception updating application: " + e.getMessage());
        }
    }
}
