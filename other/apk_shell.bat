set "dir=D:\ApkInfo" 
if not exist %dir% mkdir %dir%
echo "生成文件名称"
set YYYYmmdd=%date:~0,4%%date:~5,2%%date:~8,2%
if %time:~0,2% leq 9 (set hhmiss=%time:~1,1%%time:~3,2%%time:~6,2%) else (set hhmiss=%time:~0,2%%time:~3,2%%time:~6,2%)
set "filename=D:\ApkInfo\%YYYYmmdd%%hhmiss%.log"
echo %filename%
echo "$apk信息" >> %filename%
adb shell dumpsys package tv.icntv.ott >> %filename%
echo "$dns信息" >> %filename%
adb shell "getprop | grep dns" >> %filename%
echo "$内存信息" >> %filename%
adb shell "dumpsys meminfo tv.icntv.ott" >> %filename%
echo "$存储信息" >> %filename%
adb shell df >> %filename%
echo "$ping连通性" >> %filename%
adb shell ping -c 10 ottcn.help >> %filename%
echo "$网络状态" >> %filename%
adb shell netcfg >> %filename%
echo "$静默安装apk信息" >> %filename%
adb shell dumpsys package com.tv.icntv >> %filename%