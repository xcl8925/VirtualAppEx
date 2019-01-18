package com.lody.virtual.client.hook.proxies.shortcut;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.MethodProxy;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;
import com.lody.virtual.client.hook.utils.MethodParameterUtils;

import java.lang.reflect.Method;

import mirror.android.content.pm.ShortcutManager;
import mirror.com.android.internal.view.inputmethod.InputMethodManager;

/**
 * @author Lody
 */
public class ShortcutServiceStub extends BinderInvocationProxy {

    @TargetApi(Build.VERSION_CODES.N_MR1)
    public ShortcutServiceStub() {
        super(ShortcutManager.mService.get(
                VirtualCore.get().getContext().getSystemService(Context.SHORTCUT_SERVICE)),
                Context.SHORTCUT_SERVICE);
    }

    @Override
    public void inject() {
        Object inputMethodManager = getContext().getSystemService(Context.SHORTCUT_SERVICE);
        ShortcutManager.mService.set(inputMethodManager, getInvocationStub().getProxyInterface());
        getInvocationStub().replaceService(Context.SHORTCUT_SERVICE);
    }

    @Override
    public boolean isEnvBad() {
        Object inputMethodManager = getContext().getSystemService(Context.SHORTCUT_SERVICE);
        return InputMethodManager
                .mService.get(inputMethodManager) != getInvocationStub().getBaseInterface();
    }

    @Override
    protected void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getManifestShortcuts"));
//        addMethodProxy(new ReplaceCallingPkgMethodProxy("getDynamicShortcuts"));
//        addMethodProxy(new ReplaceCallingPkgMethodProxy("setDynamicShortcuts"));
//        addMethodProxy(new ReplaceCallingPkgMethodProxy("addDynamicShortcuts"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("createShortcutResultIntent"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("disableShortcuts"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("enableShortcuts"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getRemainingCallCount"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getRateLimitResetTime"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getIconMaxDimensions"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getMaxShortcutCountPerActivity"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("reportShortcutUsed"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("onApplicationActive"));

        addMethodProxy(new getDynamicShortcuts());
        addMethodProxy(new addDynamicShortcuts());
    }

    static class getDynamicShortcuts extends MethodProxy {

        private static final String TAG = "getDynamicShortcuts";

        @Override
        public String getMethodName() {
            return "getDynamicShortcuts";
        }

        @Override
        public boolean beforeCall(Object who, Method method, Object... args) {
            MethodParameterUtils.replaceFirstAppPkg(args);
            return super.beforeCall(who, method, args);
        }

        @Override
        public Object call(Object who, Method method, Object... args) {
            try {
                Log.e(TAG, "getDynamicShortcuts...call...");
                return method.invoke(who, args);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    static class addDynamicShortcuts extends MethodProxy {

        private static final String TAG = "addDynamicShortcuts";

        @Override
        public String getMethodName() {
            return "addDynamicShortcuts";
        }

        @Override
        public boolean beforeCall(Object who, Method method, Object... args) {
            MethodParameterUtils.replaceFirstAppPkg(args);
            return super.beforeCall(who, method, args);
        }

        @Override
        public Object call(Object who, Method method, Object... args) {
            try {
                Log.e(TAG, "addDynamicShortcuts...call...");
                return method.invoke(who, args);
            } catch (Exception e) {
                e.printStackTrace();
                return true;
            }
        }
    }
}
