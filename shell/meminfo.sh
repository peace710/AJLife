#!/usr/bin

meminfo=`adb shell dumpsys meminfo $1 | grep TOTAL | awk '{print $2;exit;}'`

echo "meminfo:$meminfo"
