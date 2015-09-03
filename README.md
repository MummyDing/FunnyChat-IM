# FunnyChat-IM

__基于Android的即时通讯应用[使用融云SDK]__

***
**Service服务端 API 接口说明**
***
###1. *注册*###

*方法名:* `register`<br>
*URL:* [localhost:8080/funnychat/register]()  
*`POST`参数:*

   名称 | 类型 | 说明
   --- | --- | ---
   `username` | String | *用户名(作为用户标识)*
   `nickname` | String | *昵  称*
   `password` | String | *密  码*

*返回值*

   名称 | 类型 | 说明
   --- | --- | ---
   `code` | String | *返回码,200为正常,404为错误*
   `userId` | String | *用户Id 标识用户身份*
   `token` | String | *用户 token,用户请求服务器的凭证*
   
*返回数据格式:*`json` 

***
###2.  *登陆*  ###

*方法名:* `login`<br>
*URL:* [localhost:8080/funnychat/login]()  
*`POST`参数:*

   名称 | 类型 | 说明
   --- | --- | ---
   `username` | String | *用户名(作为用户标识)*
   `password` | String | *密  码*
   `nickname` | String | *昵  称*
   `token` | String | *用户 token,用户请求服务器的凭证*

*返回值*

    名称 | 类型 | 说明
   --- | --- | ---
   `code` | String | *返回码,200为正常,404为错误*
   `userId` | String | *用户Id 标识用户身份*
   `token` | String | *用户 token,用户请求服务器的凭证*
   
*返回数据格式:*`json`

***
###3. *建群* ###
*方法名:* `createGroup`<br>
*URL:* [localhost:8080/funnychat/createGroup]()  
*`POST`参数:*

   名称 | 类型 | 说明
   --- | --- | ---
   `userId` | String | *用户Id 标识用户身份*
   `groupname` | String | *群 名*
   `groupId` | String | *群Id 群标识*

*返回值*

   名称 | 类型 | 说明
   --- | --- | ---
   `code` | String | *返回码,200为正常,404为错误*
   
   
*返回数据格式:*`json`
***
###4. *加群* ###
*方法名:* `addGroup`<br>
*URL:* [localhost:8080/funnychat/addGroup]()  
*`POST`参数:*

   名称 | 类型 | 说明
   --- | --- | ---
   `userId` | String | *用户Id 标识用户身份*
   `groupId` | String | *群Id 群标识*

*返回值*

   名称 | 类型 | 说明
   --- | --- | ---
   `code` | String | *返回码,200为正常,404为错误*
   
   
*返回数据格式:*`json`
 
 ***
 
###5. **退群** ###
*方法名:* `quitGroup`<br>
*URL:* [localhost:8080/funnychat/quitGroup]()  
*`POST`参数:*

   名称 | 类型 | 说明
   --- | --- | ---
   `userId` | String | *用户Id 标识用户身份*
   `groupId` | String | *群Id 群标识*

*返回值*

   名称 | 类型 | 说明
   --- | --- | ---
   `code` | String | *返回码,200为正常,404为错误*
   
   
*返回数据格式:*`json`
***
 
###6. **添加联系人** ###
*方法名:* `addContact`<br>
*URL:* [localhost:8080/funnychat/addContact]()  
*`POST`参数:*

   名称 | 类型 | 说明
   --- | --- | ---
   `userId` | String | *用户Id 标识用户身份*
   `contactId` | String | *联系人Id 群标识*

*返回值*

   名称 | 类型 | 说明
   --- | --- | ---
   `code` | String | *返回码,200为正常,404为错误*
   
   
*返回数据格式:*`json`

***
 
###7. **查询联系人** ###
*方法名:* `queryContact`<br>
*URL:* [localhost:8080/funnychat/queryContact]()  
*`POST`参数:*

   名称 | 类型 | 说明
   --- | --- | ---
   `userId` | String | *用户Id 标识用户身份*

*返回值*

   名称 | 类型 | 说明
   --- | --- | ---
   `contactId` | String | *联系人Id*
   `contactname` | String | *联系人用户名*
   
*返回数据格式:*`json`
