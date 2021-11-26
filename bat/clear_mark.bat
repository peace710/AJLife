@ECHO START
@echo off
if "%1" == "-d" (
	rm clear_mark.sh
)
if not exist "clear_mark.sh" (
	(
		echo #!/bin/bash
		echo packageName="tv.icntv.ott"
		echo if [ -n "$1" ];then
		echo 	packageName=$1 
		echo fi
		echo echo "pm clear $packageName"
		echo pm clear $packageName ^&^& mkdir -p /sdcard/Android/data/$packageName/files/DebugLog
	)>clear_mark.sh
)
powershell -NoProfile -command "((Get-Content 'clear_mark.sh') -join \"`n\") + \"`n\" | Set-Content -NoNewline 'clear_mark.sh'"
adb push clear_mark.sh /data
adb shell chmod 777 /data/clear_mark.sh
adb shell ls -l /data/clear_mark.sh
adb shell source /data/clear_mark.sh
@PAUSE