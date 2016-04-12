package com.eggheadgames.inapppayments;

import android.app.Activity;
import android.content.Context;

import com.billing.BillingService;
import com.billing.BillingServiceListener;
import com.billing.amazon.AmazonBillingService;
import com.billing.google.GoogleBillingService;

import java.util.List;

//Public front-end for IAP functionality. 

public class IAPManager {

    private static BillingService billingService;
    public static int BUILD_TARGET_GOOGLE = 0;
    public static int BUILD_TARGET_AMAZON = 1;

    public static void build(Context context, int buildTarget, List<String> iapkeys) {
        //Build-specific initializations
        if (buildTarget == BUILD_TARGET_GOOGLE) {
            billingService = new GoogleBillingService(context, iapkeys);
        } else if (buildTarget == BUILD_TARGET_AMAZON) {
            billingService = new AmazonBillingService(context, iapkeys);
        }
    }

    public static void init(String key) {
        billingService.init(key);
    }

    public static void addListener(BillingServiceListener billingServiceListener) {
        billingService.addListener(billingServiceListener);
    }

    public static void removeListener(BillingServiceListener billingServiceListener) {
        billingService.removeListener(billingServiceListener);
    }

    public static void buy(Activity activity, String sku, int id) {
        billingService.buy(activity, sku, id);
    }

    public static void destroy() {
        billingService.close();
    }

    public static BillingService getBillingService() {
        return billingService;
    }
}
