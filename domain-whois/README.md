# 简介
域名查询工具包，可以查询全球绝大部分域名后缀的域名；可以对其中一部分的查询结果进行解析，将查询结果转化为Java POJO对象。

-----

# 获取
## Maven
这是一个Maven构建的Jar工程。
你可以将这个工程Fork到本地，编译，传到本地Maven仓库管理工具，然后将Maven依赖加入到Maven的构建文件pom.xml里面来：
```xml
<dependency>
    <groupId>com.boluogan.domain</groupId>
    <artifactId>domain-whois</artifactId>
    <version>0.0.1</version>
</dependency>
```

# 非Maven构建
直接下载release/domain-whois-0.0.1.jar，加入到您自己的项目依赖路径里去。
-----
# 使用
这个Jar工具包的作用就是提供域名查询的API。
提供两种查询结果：
1）直接从whois服务器获取到的字符串。
2）将直接从whois服务器获取到的字符串转化而成的标准Java POJO对象。
两种查询结果的使用实例如下：
```java
import com.boluogan.domain.whois.WhoisQuery;
public class WhoisQueryTest {
    @Test
    public void parsedWhoisInfo() throws Exception {
        DomainWhoisInfo domainWhoisInfo = WhoisQuery.parsedWhoisInfo("boluogan.com");
        System.out.print(domainWhoisInfo);
    }


    @Test
    public void originalWhoisInfo() throws Exception {

        String whoisContent = WhoisQuery.originalWhoisInfo("boluogan.com");
        System.out.println(whoisContent);
    }


}

```
-----
# Whois字符解析
从whois查询服务器获取到的域名whois信息是一些字符串。
不同根后缀的域名的whois信息格式都不同，基本上每一种根后缀都要写一个解析器来解析。
由于经历有限，目前只写了10来种根后缀的解析器，以后再慢慢增加。
如果你愿意，也可以帮忙写一部分。

解析器和根后缀的对应关系，由程序用反射机制自动生成，原理是这样：
解析器类都在包com.boluogan.domain.whois.parser下面，类名的格式是：根后缀（首字母答谢）+WhoisParser。
例如，com域名对应的解析器类是ComWhoisParser。
根据类名生成Class对象，再用Class的实例方法newInstance()生成解析器对象，放入存储解析器对象的Map里面。

解析器类要实现WhoisParser接口。

-----
#技术实现详细说明




