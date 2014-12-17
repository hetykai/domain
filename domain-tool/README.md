# 简介


# 使用
下载release/domain-tool.zip,解压，运行run.bat(windows系统）或run.sh(unix/linux)启动程序。
启动成功后，在浏览器里输入http://localhost:18080 访问使用。


# 代码编译和运行
Maven工程，fork下来后可以导入IDE,也可以直接运行Maven命令打包：
'''Bash
mvn package
'''

打包生成的Jar可以直接运行：
'''Bash
java -jar target/domain-tool.jar
'''
启动后，在浏览器里输入访问地址:
http://localhost:18080


# REST API
##全部根后缀
地址：tlds/root/all
类型：GET

##可以解析结果的根后缀
地址：tlds/root/parseable
类型：GET


##单个域名查询
地址：whois/${domainname}
类型：GET

##批量查询
地址：bulkquery
类型：post
参数：{["sid":1,"domainname":boluogan.com],["sid":2,"domainname":"pingguogan.com"]}
返回：{"status":"success","message":"正在查询中...."}

查询结果用socket推送:
{["sid":1,"domainname":"boluogan.com","registerStatus":"已注册","createDate":"2014-12-12 18:08:30","expirationDate":"2015-12-12 18:08:30","registrant":"boluogan.com","registrantEmail":"domain@boluogan.com","registrar":"Godaddy"],[.......]}


##保存查询结果
地址：saveresult
类型：post
参数：{["sid":1,"domainname":"boluogan.com","registerStatus":"已注册","createDate":"2014-12-12 18:08:30","expirationDate":"2015-12-12 18:08:30","registrant":"boluogan.com","registrantEmail":"domain@boluogan.com","registrar":"Godaddy"],[.......]}
返回：{"status":"success","message":"保存成功"}

##历史查询记录
地址：queries
类型：GET
返回：{["name":"三拼域名查询 2014-12-17","url":"/datas/201412/20141217203508.json"],[....]}
