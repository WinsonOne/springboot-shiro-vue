# springboot-shiro-vue
提供一套基于Springboot、Shiro、JWT、Element UI框架的权限管理系统，便于开发前后端分离的管理系统

## 开发工具

1. 后端开发工  
后端使用IntelliJ IDEA作为开发工具，另外为了快速开发，需要IDEA中安装lombok插件。其中lombok可以避免编写对象模型的get、set方法。

2. 前端开发工具  
前端是一个基于vue和element ui的项目，使用最新的前端技术栈。使用Visual Studio Code作为开发工具，配合Vetur、ESLint等扩展插件来保证开发代码的质量。

## 技术选型
1. springboot 2.1.8
2. mybatis
3. pagehelper
4. shiro
5. jwt

## 数据库表设计  
本系统基于角色的权限访问控制(Role-Based Access Control)作为访问控制。在RBAC中，权限与角色相关联，用户通过成为适当角色的成员而得到这些角色的权限。这就极大地简化了权限的管理。

1. 系统用户表(t_sys_user)  

| 字段名  | 注释 | 数据类型  | 是否允许为Null |  
| ------------- | ------------- |------------- | ------------- |  
| user_id  | 用户ID，主键，自增  | bigint  | 否  |  
| username  | 用户名，登录账户  | varchar(64)  | 否  |
| nickname  | 用户昵称  | varchar(64)  | 否  |
| password  | 密码，密文存储  | varchar(64)  | 否  |
| salt  | 密码加密盐值  | varchar(64)  | 否  |
| mobile  | 移动手机号  | varchar(11)  | 否  |
| email  | 电子邮箱地址  | varchar(128)  | 否  |
| state  | 用户状态，1有效，0禁用  | int  | 否  |
| is_delete  | 是否已删除，1是，0否  | tinyint  | 否  |
| create_time  | 创建时间  | datetime  | 否  |
| create_user_id  | 创建人ID  | bigint  | 否  |
| update_time  | 修改时间  | datetime  | 是  |
| update_user_id  | 修改人ID  | bigint  | 是  |

2. 系统角色表(t_sys_role)

| 字段名  | 注释 | 数据类型  | 是否允许为Null |  
| ------------- | ------------- |------------- | ------------- |  
| role_id  | 角色ID，主键，自增  | bigint  | 否  |  
| role_name  | 角色名称  | varchar(64)  | 否  |
| remark  | 备注信息  | varchar(256)  | 是  |
| state  | 角色状态，1有效，0禁用  | int  | 否  |
| is_delete  | 是否已删除，1是，0否  | tinyint  | 否  |
| create_time  | 创建时间  | datetime  | 否  |
| create_user_id  | 创建人ID  | bigint  | 否  |
| update_time  | 修改时间  | datetime  | 是  |
| update_user_id  | 修改人ID  | bigint  | 是  |

3. 用户角色关系表(t_user_role)  

| 字段名  | 注释 | 数据类型  | 是否允许为Null |  
| ------------- | ------------- |------------- | ------------- |  
| user_role_id  | 角色ID，主键，自增  | bigint  | 否  |  
| user_id  | 用户ID  | bigint  | 否  | 
| role_id  | 角色ID  | bigint  | 否  |  
| create_time  | 创建时间  | datetime  | 否  |
| create_user_id  | 创建人ID  | bigint  | 否  |

用户角色关系表，不需要进行修改，用户授权时，重新生成关联关系  

4. 系统资源表(t_sys_resource)

| 字段名  | 注释 | 数据类型  | 是否允许为Null |  
| ------------- | ------------- |------------- | ------------- |  
| resource_id  | 资源ID，主键，自增  | bigint  | 否  |  
| resource_name  | 资源名称  | varchar(64)  | 否  |
| resource_type  | 资源类型，1目录，2菜单，3按钮  | int  | 否  |
| parent_id  | 上级资源ID  | bigint  | 是  |  
| parent_ids  | 所有上级资源ID  | varchar(128)  | 是  |  
| icon  | 图标  | varchar(64)  | 是  |  
| path  | 访问路径  | varchar(64)  | 是  |  
| perm  | 资源对应的权限  | varchar(128)  | 是  |  
| remark  | 备注信息  | varchar(256)  | 是  |
| state  | 资源状态，1有效，0禁用  | int  | 否  |
| is_delete  | 是否已删除，1是，0否  | tinyint  | 否  |
| create_time  | 创建时间  | datetime  | 否  |
| create_user_id  | 创建人ID  | bigint  | 否  |
| update_time  | 修改时间  | datetime  | 是  |
| update_user_id  | 修改人ID  | bigint  | 是  |

5. 角色资源关系表(t_role_resource)

| 字段名  | 注释 | 数据类型  | 是否允许为Null |  
| ------------- | ------------- |------------- | ------------- |  
| role_resource_id  | 角色ID，主键，自增  | bigint  | 否  |  
| role_id  | 角色ID  | bigint  | 否  |  
| resource_id  | 资源ID  | bigint  | 否  |  
| create_time  | 创建时间  | datetime  | 否  |
| create_user_id  | 创建人ID  | bigint  | 否  |

角色资源关系表，不需要进行修改，角色授权时，重新生成关联关系