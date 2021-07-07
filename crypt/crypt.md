# 加解密与编解码
## 编码解码 Encoding & Decoding
Base64</br>
将二进制数据转换成由64个字符组成的字符串的编码算法（A—Za-z0-9+/）</br>
用途：让原数据具有字符串所具有的特性，如可以放在URL中传输、可以保存到文本文件，可以通过普通聊天软件进行文本传输。</br>
把原本可读懂的字符串变成读不懂的字符串，降低偷窥风险。

Base64编码表</br>
![-w585](https://github.com/peace710/AJLife/blob/master/crypt/image/16255615850052.jpg)

示例：
字符串"Man" Base64编码</br>
![-w535](https://github.com/peace710/AJLife/blob/master/crypt/image/16255616653433.jpg)

Base64编码不足的补齐方式</br>
![-w492](https://github.com/peace710/AJLife/blob/master/crypt/image/16255617321326.jpg)
</br>
![-w469](https://github.com/peace710/AJLife/blob/master/crypt/image/16255617509791.jpg)


URL encoding
将URL中保留字符串使用百分号"%"进行编码</br>
目的：消除歧义，避免解析错误</br>

示例：</br>
`https://www.baidu.com/?name=你 好`</br>
上述网址中，你好中间的空格会被编码成"%20"</br>
`https://www.baidu.com/?name=你%20好`</br>

## 加密与解密 Encryption & Decryption
不仅可用于文本内容加解密，还可以应用于二进制流的加解密

对称加密
原理：使用密钥和加密算法对数据进行转换，得到的无意义数据即为密文；</br>
使用密钥和解密算法对密文进行逆向转换，得到原始数据。
常见算法：DES,AES,3DES</br>
![-w580](https://github.com/peace710/AJLife/blob/master/crypt/image/16256224266526.jpg)

缺点：不能在不安全⽹络上传输密钥，⼀旦密钥泄露则加密通信失败

非对称加密
原理：使用公钥对数据进行加密得到密文；使用私钥对数据进行解密得到原数据。</br>
常见算法：RSA,DSA</br>
其他应用：数字签名</br>

公钥加密</br>
![-w599](https://github.com/peace710/AJLife/blob/master/crypt/image/16256224717929.jpg)

私钥验签</br>
![-w610](https://github.com/peace710/AJLife/blob/master/crypt/image/16256226139980.jpg)

签名验证时，一般会计算出原数据的hash，然后再签名验证</br>
原因是绝大多数情况下原数据会比较大，直接进行签名，签名所占的时间比较长，空间也会比较大，影响性能。</br>
优点：可以在不安全⽹络上传输密钥</br>
缺点：数据的加解密计算复杂，计算上的时间性能比对称加密差</br>

## 压缩与解压缩 Compression & Decompression
压缩：把数据换一种方式来存储，以减小存储空间</br>
解压缩：把压缩后的数据还原成原先的形式，以便使用</br>
常用压缩算法：DEFLATE、JPEG、MP3</br>

## 序列化 Serialization
序列化：把数据对象（一般内存中的，例JVM中的对象）转换成字节序列的过程</br>
反序列化：把字节序列重新转换成内存中的对象</br>
作用：让内存中的对象可以被存储与传输</br>

## 哈希 Hash
定义：把任意数据转换成指定大小范围（通常很小）的数据</br>
作用：摘要，数字指纹</br>
常见算法：MD5、SHA1、SHA256等</br>
用途：
* 数据完整性验证;
* 快速查找hashCode()和HashMap;
* 隐私保护

## 字符集 Charset
定义：一个由整数向现实中的文字符号映射集
* ASCII:128个字符，1字节
* ISO-8859-1:对ASCII进行扩充，1字节
* Unicode:13万个字符，多字节
    1. UTF-8:Unicode编码分支
    2. UTF-16:Unicode编码分支
