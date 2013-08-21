-------------------- [ first time ] start --------------------
/// alternative also store and compare version
final String PREFS_NAME = "MyPrefsFile";

SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

if (settings.getBoolean("my_first_time", true)) {
	//the app is being launched for first time, do something
	// first time task
	settings.edit().putBoolean("my_first_time", false).commit();
}


-------------------- [ first time ][ version ] start --------------------
public enum AppStart {
    FIRST_TIME, FIRST_TIME_VERSION, NORMAL;
}

private final SharedPreferences sharedPreferences;
private final Context context;
/**
 * The app version code (not the version name!) that was used on the last
 * start of the app.
 */
private static final String LAST_APP_VERSION = "last_app_version";

/**
 * Caches the result of {@link #checkAppStart()}. To allow idempotent mehtod
 * calls.
 */
private static AppStart appStart = null;

/**
 * Finds out started for the first time (ever or in the current version).
 * 
 * @return the type of app start
 */
public AppStart checkAppStart() {
    if (appStart == null) {
        PackageInfo pInfo;
        try {
            pInfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            int lastVersionCode = sharedPreferences.getInt(
                    LAST_APP_VERSION, -1);
            // String versionName = pInfo.versionName;
            int currentVersionCode = pInfo.versionCode;
            appStart = checkAppStart(currentVersionCode, lastVersionCode);
            // Update version in preferences
            sharedPreferences.edit()
                    .putInt(LAST_APP_VERSION, currentVersionCode).commit();
        } catch (NameNotFoundException e) {
            Log.w(Constants.LOG,
                    "Unable to determine current app version from pacakge manager. Defenisvely assuming normal app start.");
        }
    }
    return appStart;
}

public AppStart checkAppStart(int currentVersionCode, int lastVersionCode) {
    if (lastVersionCode == -1) {
        return AppStart.FIRST_TIME;
    } else if (lastVersionCode < currentVersionCode) {
        return AppStart.FIRST_TIME_VERSION;
    } else if (lastVersionCode > currentVersionCode) {
        Log.w(Constants.LOG, "Current version code (" + currentVersionCode
                + ") is less then the one recognized on last startup ("
                + lastVersionCode
                + "). Defenisvely assuming normal app start.");
        return AppStart.NORMAL;
    } else {
        return AppStart.NORMAL;
