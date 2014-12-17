# 简介


# 使用
下载release/domain-tool.zip,解压，运行run.bat(windows系统）或run.sh(unix/linux)启动程序。<br>
启动成功后，在浏览器里输入http://localhost:18080 访问使用。


# 代码编译和运行
Maven工程，fork下来后可以导入IDE,也可以直接运行Maven命令打包：<br>
>mvn package


打包生成的Jar可以直接运行：<br>
>java -jar target/domain-tool.jar
<br>
启动后，在浏览器里输入访问地址:<br>
>http://localhost:18080


# REST API
##全部根后缀
>地址：tlds/root/all<br>
>类型：GET

##可以解析结果的根后缀
>地址：tlds/root/parseable<br>
>类型：GET<br>


##单个域名查询
>地址：whois/${domainname}<br>
>类型：GET<br>

##批量查询
>地址：bulkquery<br>
>类型：post<br>
>参数：{["sid":1,"domainname":boluogan.com],["sid":2,"domainname":"pingguogan.com"]}<br>
>返回：{"status":"success","message":"正在查询中...."}<br>
>查询结果用socket推送:<br>
>{["sid":1,"domainname":"boluogan.com","registerStatus":"已注册","createDate":"2014-12-12 18:08:30","expirationDate":"2015-12-12 18:08:30","registrant":"boluogan.com","registrantEmail":"domain@boluogan.com","registrar":"Godaddy"],[.......]}<br>


##保存查询结果
>地址：saveresult<br>
>类型：post<br>
>参数：{["sid":1,"domainname":"boluogan.com","registerStatus":"已注册","createDate":"2014-12-12 18:08:30","expirationDate":"2015-12-12 18:08:30","registrant":"boluogan.com","registrantEmail":"domain@boluogan.com","registrar":"Godaddy"],[.......]}<br>
>返回：{"status":"success","message":"保存成功"}

##历史查询记录
>地址：queries<br>
>类型：GET<br>
>返回：{["name":"三拼域名查询 2014-12-17","url":"/datas/201412/20141217203508.json"],[....]}<br>
