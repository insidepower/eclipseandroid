adb push bin/su /system/bin
adb shell chmod 4755 /system/bin/su
adb uninstall koushikdutta.superuser
adb install bin/Superuser.apk
