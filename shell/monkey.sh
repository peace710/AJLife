#!/bin/bash

monkey(){
	adb shell monkey -p $packageName --throttle $throttle -v $count 2>&1 | tee $fileName.log
	echo "monkey end"
	kill $1
}

echo "args nums is $#"

readKey=1
packageName=me.peace.android
throttle=50
count=500


for arg in $*
do
	if [ "$readKey" = "1" ];then
		readKey=0
		key=$arg
	else
		readKey=1
		if [ "$key" = "-p" ];then
			packageName=$arg
		elif [ "$key" = "-t" ];then
			throttle=$arg
		elif [ "$key" = "-v" ];then
			count=$arg
		else
			exit "error params $key"
		fi
	fi
done

fileName=$(date "+%Y%m%d%H%M%S")
logFile=$fileName.cat

adb logcat -c

adb logcat -v threadtime > $logFile.log &

log=$!

echo "current process id is $log"

monkey $log &



