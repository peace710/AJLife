#!/bin/bash

packageName=$1
count=$2
pid=`adb shell pgrep $packageName`

echo "start get apk info"

if [ -f data.txt ];then
	rm data.txt
fi

int=1
while (( $int<=$count ))
do
	echo "get apk info [$int]"
	adb shell top -p $pid -n 1 | grep $packageName	> file.txt
	awk '$1=="'$pid'"{print strftime("%T")"\t"$9"\t"$10;exit;}' file.txt >> data.txt
#	awk '$1=="'$pid'"{print "'$int'""\t"$9"\t"$10;exit;}' file.txt >> data.txt
	let "int++"
	sleep 10 
done
echo "end get apk info"
rm file.txt

mem_b=`adb shell free | grep Mem: | awk '{print $2}'`

mem_m=`expr $mem_b / 1024 / 1024`

gnuplot -c "apkplot.txt" $packageName "$mem_m MB"

echo "apkinfo success"
