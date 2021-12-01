@ECHO off
set packageName=me.peace.android
if "%1" == "-p" (
	if not "%2" == "" (
		set packageName=%2
	)
)
echo %packageName%
set YYYYmmdd=%date:~0,4%%date:~5,2%%date:~8,2%
if %time:~0,2% leq 9 (set hhmiss=%time:~1,1%%time:~3,2%%time:~6,2%) else (set hhmiss=%time:~0,2%%time:~3,2%%time:~6,2%)
set "filename=%YYYYmmdd%%hhmiss%.log"
echo %filename%
adb root
adb shell mkdir -p /sdcard/Android/data/%packageName%/files/DebugLog
adb shell am force-stop %packageName%
adb logcat -v threadtime > %filename%