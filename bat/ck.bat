@echo off
set packageName=me.peace.android
if "%1" == "-p" (
	if not "%2" == "" (
		set packageName=%2
	)
)
echo %packageName%
adb root
adb shell pm clear %packageName%
adb shell mkdir -p /sdcard/Android/data/%packageName%/files/DebugLog