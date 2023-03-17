[TOC]

# 1.JavaMail

## 1.JavaMail发送简单邮件

### 1.添加依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
```



### 2.配置文件设置

```properties
spring.mail.host=smtp.qq.com
spring.mail.username=1817661645
spring.mail.password=atnysyaywkxiifjj
spring.mail.protocol=smtp
spring.mail.port=25
```



### 3.编写函数

```java
private void sendMail(String[] to, String subject, String template, Map<String, Object> args) throws MessagingException {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("analysis_report@qq.com");           // 设置发件人邮箱（若配置默认邮箱则不用再设置）
    message.setTo("1176302809@qq.com");            // 设置收件人邮箱
    message.setSubject("123123");                  // 设置邮件主题
    message.setSentDate(new Date());
    // 设置邮件发送时间
	message.setText("123123123");
    //发送
    mailSender.send(mimeMessage);
}
```





## 2.JavaMail发送HTML邮件

### 1.引入依赖

在上面的基础上，引入*thymeleaf*来渲染HTML页面

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
<dependency>
    <groupId>ognl</groupId>
    <artifactId>ognl</artifactId>
    <version>3.3.4</version>
</dependency>
```



### 2.配置文件

配置文件如上，但是需要渲染的HTML模板需要放在*resources/templates*下



### 3.编写函数

首先提供一个渲染函数

```java
private String render(String template, Map<String, Object> args) {
    ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
    //set path of templates
    resolver.setPrefix("/templates/");
    resolver.setSuffix(".html");
    //get engine
    TemplateEngine engine = new TemplateEngine();
    engine.setTemplateResolver(resolver);

    //set variables
    Context context = new Context();
    context.setVariables(args);
    //render
    return engine.process(template, context);
}
```



然后使用JavaMail中的*MimeMessage*来发送渲染后的邮件

```java
private void sendMail(String[] to, String subject, String template, Map<String, Object> args) throws MessagingException {
    
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

    mimeMessageHelper.setFrom("analysis_report@qq.com");

    mimeMessageHelper.setTo(to);
    mimeMessageHelper.setSubject(subject);

    String mailText = render(template, args);
    mimeMessageHelper.setText(mailText, true);
    //发送
    mailSender.send(mimeMessage);
}
```

