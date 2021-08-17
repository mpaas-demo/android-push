# mPaaS Push Demo

## 介绍

本代码用于指导如何集成mPaaS消息推送组件，详细文档请参考[官网](https://help.aliyun.com/document_detail/49586.html?spm=a2c4g.11186623.6.723.4b81597e7XsxwJ)。


## 编译

本代码出于发布便捷性考虑，将inside和aar集成方式合并在一个工程中，您可通过修改配置来编译不同的目标类型。

### Inside编译
1. gradle.properties里mPaasBuildType设置为inside

### AAR编译
1. gradle.properties里mPaasBuildType设置为aar


## 其他
* 出于安全考虑，厂商通道所使用的密钥信息已从代码中移除，因此厂商通道在demo应用中无法正常运行。
* 如需连接自己的环境测试，可替换项目中的 Ant-mpaas-xxxxxx.config 为自己的配置文件，同时更换 demo 的签名为自己的签名。
