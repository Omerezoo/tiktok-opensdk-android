package com.bytedance.sdk.open.aweme.authorize.model;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.bytedance.sdk.open.aweme.common.constants.BDOpenConstants;
import com.bytedance.sdk.open.aweme.common.constants.TikTokConstants;
import com.bytedance.sdk.open.aweme.common.model.BaseReq;
import com.bytedance.sdk.open.aweme.common.model.BaseResp;


/**
 * auth 请求
 * Created by yangzhirong on 2018/10/8.
 */
public class Authorization {

    public static class Request extends BaseReq {

        public String state;
        public String redirectUri;
        public String clientKey;
        @Deprecated
        public int targetApp = TikTokConstants.TARGET_APP.TIKTOK; //默认tiktok

        /**
         * 可选项，支持wap授权页面横竖屏方式，取值ActivityInfo.ScreenOrientation
         */
        public int wapRequestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;
        /**
         * 必选权限，多个以逗号分割
         * 传入格式：scope = "user_info,friend_relation";
         */
        public String scope;
        /**
         * 可选权限(默认不勾选)，多个以逗号分割
         * 传入格式：optionalScope = "message,friend_relation";
         */
        public String optionalScope0;
        /**
         * 可选权限(默认勾选)，多个以逗号分割
         * 传入格式：optionalScope = "message,friend_relation";
         */
        public String optionalScope1;

        public String getClientKey() {
            return clientKey;
        }

        public Request() {
            super();
        }

        public Request(Bundle bundle) {
            fromBundle(bundle);
        }

        @Override
        public int getType() {
            return TikTokConstants.ModeType.SEND_AUTH_REQUEST;
        }

        @Override
        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.state = bundle.getString(BDOpenConstants.AuthParams.STATE);
            this.clientKey = bundle.getString(BDOpenConstants.AuthParams.CLIENT_KEY);
            this.redirectUri = bundle.getString(BDOpenConstants.AuthParams.REDIRECT_URI);
            this.scope = bundle.getString(BDOpenConstants.AuthParams.SCOPE);
            this.optionalScope0 = bundle.getString(BDOpenConstants.AuthParams.OPTIONAL_SCOPE0);
            this.optionalScope1 = bundle.getString(BDOpenConstants.AuthParams.OPTIONAL_SCOPE1);
            this.wapRequestedOrientation = bundle.getInt(BDOpenConstants.AuthParams.WAP_REQUESETED_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        }

        @Override
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString(BDOpenConstants.AuthParams.STATE, state);
            bundle.putString(BDOpenConstants.AuthParams.CLIENT_KEY, clientKey);
            bundle.putString(BDOpenConstants.AuthParams.REDIRECT_URI, redirectUri);
            bundle.putString(BDOpenConstants.AuthParams.SCOPE, scope);
            bundle.putString(BDOpenConstants.AuthParams.OPTIONAL_SCOPE0, optionalScope0);
            bundle.putString(BDOpenConstants.AuthParams.OPTIONAL_SCOPE1, optionalScope1);
            bundle.putInt(BDOpenConstants.AuthParams.WAP_REQUESETED_ORIENTATION, wapRequestedOrientation);
        }
    }

    public static class Response extends BaseResp {

        public String authCode;
        public String state;
        public String grantedPermissions;

        public Response() {
        }

        public Response(Bundle bundle) {
            fromBundle(bundle);
        }

        @Override
        public int getType() {
            return TikTokConstants.ModeType.SEND_AUTH_RESPONSE;
        }

        @Override
        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.authCode = bundle.getString(BDOpenConstants.AuthParams.AUTH_CODE);
            this.state = bundle.getString(BDOpenConstants.AuthParams.STATE);
            this.grantedPermissions = bundle.getString(BDOpenConstants.AuthParams.GRANTED_PERMISSION);

        }

        @Override
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString(BDOpenConstants.AuthParams.AUTH_CODE, authCode);
            bundle.putString(BDOpenConstants.AuthParams.STATE, state);
            bundle.putString(BDOpenConstants.AuthParams.GRANTED_PERMISSION, grantedPermissions);
        }
    }
}