#!/bin/bash

mem_b=`adb shell free | grep Mem: | awk '{print $2}'`

mem_m=`expr $mem_b / 1024 / 1024`

echo "$mem_m MB"

