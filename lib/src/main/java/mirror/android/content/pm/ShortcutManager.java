package mirror.android.content.pm;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.IInterface;

import mirror.RefClass;
import mirror.RefObject;

@TargetApi(Build.VERSION_CODES.N_MR1)
public class ShortcutManager {
    public static Class<?> TYPE = RefClass.load(ShortcutManager.class, android.content.pm.ShortcutManager.class);
    public static RefObject<IInterface> mService;
}
