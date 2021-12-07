#!/usr/bin

pid=`adb shell pgrep $1`

filter="$pid/$1:"

cpu_usage=`adb shell dumpsys cpuinfo | grep $filter | awk '{print $1}'`

echo "cpu_usage:$cpu_usage"



