# PageHelper integration with Spring Boot

PageHelper-Spring-Boot-Starter 帮助你集成分页插件到 Spring Boot。

PageHelper-Spring-Boot-Starter will help you use PageHelper with Spring Boot.

Support PageHelper 5.x

## How to use
在 pom.xml 中添加如下依赖：

Add the following dependency to your pom.xml: 
```xml
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Example
>https://github.com/abel533/MyBatis-Spring-Boot

## PageHelper
>https://github.com/pagehelper/Mybatis-PageHelper

## Special Configurations
一般情况下，你不需要做任何配置。

Normally, you don't need to do any configuration.

如果需要配置，可以使用如下方式进行配置：

You can config PageHelper as the following:

application.properties:
```properties
pagehelper.propertyName=propertyValue
```
注意 pagehelper 配置，因为分页插件根据自己的扩展不同，支持的参数也不同，所以不能用固定的对象接收参数，所以这里使用的 `Map<String,String>`，因此参数名是什么这里就写什么，IDE 也不会有自动提示。

关于可配置的属性请参考 [如何使用分页插件](https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md)。

You can configure the properties of the reference here [How to use the PageHelper](https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/en/HowToUse.md).

## Interceptor Order

如果你想要控制 拦截器插件的顺序，可以通过下面注解控制：

If you want to control the order in which the interceptor plug-in, you can use the following annotation control:
```java
@AutoConfigureAfter(PageHelperAutoConfiguration.class)
//Or
@AutoConfigureBefore(PageHelperAutoConfiguration.class)
```