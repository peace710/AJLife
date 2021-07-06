#Http
Http超文本传输协议，运行于TCP层之上，是个应用层协议。Http指定了浏览器/客户端发送什么样请求到服务器，服务器又是以什么样内容响应浏览器/客户端。

###通信过程
（1）浏览器/客户端与服务器建立连接
（2）浏览器/客户端发送请求至服务器
（3）服务器接受请求，根据请求内容进行处理响应
（4）浏览器/客户端与服务器断开连接

###http地址格式
[协议类型][服务器地址][路径]
举例：http://github.com/peace710?tab=repositories
协议类型：http://
服务器地址：github.com
路径(Path)：/peace710?tab=repositories

### http请求与响应
#####Request
```
POST /peace710?tab=repositories HTTP/1.1 
Host:github.com
Content-Type:text/plain
Content-Length:16

Please follow me
```
#####Response
```
HTTP/1.1 200 OK
Content-Type:application/json;charset=utf-8
Cache-Control:public,max-age=60,s-maxage=60
Vary:Accept,Accept=Encoding
Content-Encoding:gzip

[{"id":"10086","username":"peace710","age":20}]
```

####Request Method
请求方式
* GET 获取资源；没有Body
* POST 增加或者更改资源；有Body
* PUT 更改资源；有Body
* DELETE 删除资源；没有Body
* HEAD 与GET类似，返回不会有Body,比如下载文件之前返回的信息

#### Response Status Code
响应状态码
* 1xx 临时性消息
  100 （继续）请求者应当继续发起请求。服务器已收到部分请求，正在等待其他部分
  101 （切换协议）请求者要求服务器切换协议，服务器确认并准备切换。比如HTTP/2
   
* 2xx 成功
  200 （成功）服务器成功处理了请求
  206 （部分内容）服务器成功处理了部分GET请求
  
* 3xx 重定向
  301 （永久移动）请求的内容永久移动到新位置。服务器返回此响应，对于GET或HEAD请求的响应，会自动重定向至新位置
  304 （未修改）请求响应的内容未更改，服务器返回此响应，但不会返回结果内容
  
* 4xx 客户端错误
  401 （未授权）请求需要身份验证   
  404 （未找到）服务器找不到请求的内容

* 5xx 服务器错误   
  500 （内部错误）服务器内部出错，无法完成请求

#####Header
HTTP消息的元数据（metadata）
* Host:服务器主机地址，采用DNS域名解析寻址
* Content-Type/Content-Length:内容的类型/长度
* Location:重定向的目标URL
* User-Agent:用户代理
* Range/Accept-Range:指定Body的内容范围,如多线程分片段下载
* Cookie/Set-Cookie:发送Cookie/设置Cookie
* Authorization:授权信息,如Auth授权认证

客户端要求的设置
* Accept:客户端能接受的数据类型，如text/html
* Accept-Charset:客户端接受的字符集，如utf-8
* Accept-Encoding:客户端接受的压缩编码类型，如gzip
* Content-Encoding:压缩类型，如gzip

缓存相关的设置
* Cache-Control:
  请求使用
  no-cache:服务不使用缓存数据
  no-store:不缓存数据报文
  max-age:告诉服务器接收不大于max-age的缓存数据
  only-if-cached:告诉服务器，如果有就取缓存数据
  
  响应使用
  public:任何情况下使用缓存
  private:private=name,仅缓存指定内容，或者不缓存
  
* Last-Modified:服务器返回上次的修改时间
* If-Modified-Since:该报文内容如果未发生变更，即上次修改时间未变更，返回304，否则返回最新数据，请求时携带
* Etag:服务器返回的报文hash
* If-None-Match:该报文内容如果未发生变更，即上次的内容hash未变更，返回304，否则返回最新数据，请求时携带

#####Body
* Content-Length:内容的长度（字节）
* Content-Type:内容的类型
    1. text/html:html文本，用于浏览器页面的响应
    2. application/x-www-form-urlencode:普通表单，encode URL格式
    3. multipart/form-data:多部分形式，一般用于传输包含二进制的多项内容
    4. application/json:json形式，用Api响应或POST/PUT请求
    5. image/jpeg:图片文件，用于Api响应或POST/PUT请求
    6. application/zip:压缩文件，用于Api响应或POST/PUT请求
* Transfer-Encoding:chunked
  表示Body长度无法确定，Content-Length不能使用
  Body格式:
  length1
  data1
  length2
  data2
  0
  以0+换行表示内容结束
  
  举例
  ```
    HTTP/1.1 200 OK
    Transfer-Encoding:chunked
    
    6
    Please
    
    6
    Follow
    
    2
    Me
    
    0
  ```



  