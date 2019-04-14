package com.brommko.android.zeroqode;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by dragank on 10/1/2016.
 */
public class AdMob {
    private static final String TAG = "ADMOB";

    private InterstitialAd mInterstitialAd;
    private Context mContext;
    private AdView adView;

    public void destroy() {
        mInterstitialAd = null;
        adView = null;
    }

    public AdMob(Context mContext, AdView adView) {
        this.mContext = mContext;
        this.adView = adView;
        initInterstitialAd();
        initBannerAds();
    }

    public void initInterstitialAd() {
        String interstitialId = mContext.getString(R.string.interstitial_ad_unit_id);
        if (!TextUtils.isEmpty(interstitialId)) {
            mInterstitialAd = new InterstitialAd(mContext);
            mInterstitialAd.setAdUnitId(interstitialId);
            mInterstitialAd.setAdListener(new AdListener() {
                public void onAdLoaded() {
                    if (mInterstitialAd != null) {
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        }
                    }
                }
            });
        }
    }

    public void initBannerAds() {
        MobileAds.initialize(mContext, mContext.getString(R.string.ad_app_id));
        if (adView != null) {
            String bannerId = mContext.getString(R.string.banner_ad_unit_id);
            if (!TextUtils.isEmpty(bannerId)) {
                adView.setVisibility(View.VISIBLE);
            } else {
                adView.setVisibility(View.GONE);
            }
        }
    }

    public void requestInterstitialAd() {
        ((MainActivity)mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mInterstitialAd != null) {
                    String interstitialId = mContext.getString(R.string.interstitial_ad_unit_id);
                    if (!TextUtils.isEmpty(interstitialId)) {
                        if (SuperViewWeb.isActivityVisible()) {
                            mInterstitialAd.loadAd(getAdRequest());
                        }
                    }
                }
            }
        });

    }

    public void requestBannerAds() {
        ((MainActivity)mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (adView != null) {
                    String bannerId = mContext.getString(R.string.banner_ad_unit_id);
                    if (!TextUtils.isEmpty(bannerId)) {
                        adView.loadAd(getAdRequest());
                    }
                }
            }
        });

    }

    private AdRequest getAdRequest() {
        return new AdRequest.Builder()
                .addTestDevice(mContext.getString(R.string.test_device_id))
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
    }
}