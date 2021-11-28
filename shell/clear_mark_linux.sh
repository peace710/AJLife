#!/bin/bash
if [ -n "$1" ] && [ "$1"="-d" ];then
	rm clear_mark.sh
fi
if [ ! -f "clear_mark.sh" ];then
	codes=('#!/bin/bash\n'
	'packageName="me.peace.android"\n'
	'if [ -n "$1" ];then\n'
	'\tpackageName=$1\n'
	'fi\n'
	'echo "pm clear $packageName"\n'
	'pm clear $packageName && mkdir -p /sdcard/Android/data/$packageName/files/DebugLog')
	IFS=""
	echo "${codes[*]}" > clear_mark.sh
	unset IFS
	fi

adb root
adb push clear_mark.sh /data
adb shell chmod 777 /data/clear_mark.sh
adb shell ls -l /data/clear_mark.sh
adb shell source /data/clear_mark.sh
