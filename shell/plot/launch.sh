#!/bin/bash

file="launch.txt"

packageName="me.peace.android"
activity="me.peace.android.PeaceActivity"

count=10
maxTime=0
minTime=0

if [ -n "$1" ];then
	if [ "$1" = "-p" ];then
		packageName=$2
		activity=$3
	elif [ "$1" = "-c" ];then
		count=$2
	elif [ "$1" = "-pc" ];then
		packageName=$2
		activity=$3
		count=$4
	fi
fi

totalTime=0
int=0

if [ -f "$file" ];then
	rm $file
fi

echo "#start $packageName/$activity" >> $file

while (($int < $count))
do
	adb shell am force-stop $packageName
	time=`adb shell am start -W -n $packageName/$activity | grep TotalTime | cut -d ' ' -f 2`
	sleep 8
	if [[ $maxTime == 0 || $time > $maxTime ]];then
		maxTime=$time
	fi
	if [[ $minTime == 0 || $time < $minTime ]];then
		minTime=$time
	fi
	let "int++"
	echo "$int start $packageName/$activity,cost $time ms "
	echo -e "$int\t$time" >> $file
	let "totalTime+=time"
done

averageTime=`echo "scale=2;$totalTime / $int"|bc`

echo "maxTime: $maxTime ms,minTime: $minTime ms,averageTime: $averageTime ms,total count: $count"

echo "#maxTime: $maxTime ms,minTime: $minTime ms,averageTime: $averageTime ms,total count: $count" >> $file

export_png="launch.png"

if [ -f "$export_png" ];then
	rm $export_png 
 fi

gnuplot -persist << EOF
load "gnuplot.txt"
EOF

echo "gnuplot success"
