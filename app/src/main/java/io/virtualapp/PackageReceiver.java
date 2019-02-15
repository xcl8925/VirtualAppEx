package io.virtualapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import io.virtualapp.home.HomeActivity;

public class PackageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        String action = intent.getAction();
        String packageName = intent.getData().getEncodedSchemeSpecificPart();

        if (action.equals(Intent.ACTION_PACKAGE_ADDED)) {
            Log.i("compatibilityTest", ">>>>>>packageName added = " + packageName);
            if (!packageName.equals("io.virtualapp.addon.arm64")) {
                Intent mIntent = new Intent(HomeActivity.ACTION_TO_ADD_PACKAGE);
                mIntent.putExtra(HomeActivity.TO_ADD_OR_REMOVE_PACKAGE_NAME, packageName);
                LocalBroadcastManager.getInstance(VApp.getApp()).sendBroadcast(mIntent);
            }
        } else if (action.equals(Intent.ACTION_PACKAGE_INSTALL)) {
            Log.i("compatibilityTest", ">>>>>>packageName install = " + packageName);
        } else if (action.equals(Intent.ACTION_PACKAGE_REMOVED)) {
            Log.i("compatibilityTest", ">>>>>>packageName removed = " + packageName);
            if (!packageName.equals("io.virtualapp.addon.arm64")) {
                Intent mIntent = new Intent(HomeActivity.ACTION_TO_REMOVE_PACKAGE);
                mIntent.putExtra(HomeActivity.TO_ADD_OR_REMOVE_PACKAGE_NAME, packageName);
                LocalBroadcastManager.getInstance(VApp.getApp()).sendBroadcast(mIntent);
            }
        } else if (action.equals(Intent.ACTION_MY_PACKAGE_REPLACED)) {
            Log.i("compatibilityTest", ">>>>>>packageName replaced = " + packageName);
        } else {
        }
    }
}