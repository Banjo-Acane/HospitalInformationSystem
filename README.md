HospitalInformationSystem (HIS)

# 云医院系统

##### 系统背景：

  实现东软HIS云医院系统挂号、退号，医生诊断、开药，收款结算功能的基础上，主要围绕HIS系统的门诊功能的进一步实现和优化。

##### 系统的功能需求：

  实践应用系统《东软云医院管理系统》门诊模块，包括门诊医生站功能、管理员功能、分诊功能和医生诊断功能。
  门诊医生站的主要功能包括：门诊病历首页、检查申请、成药处方、诊毕等。
  待诊患者列表，已诊患者列表和未诊患者列表的显示、刷新、查询，显示待诊患者的历史就诊记录。



##### 部分界面展示及功能说明

1. ###### 登录界面

   含勾选自动填写密码登录后，下次登录时自动填写密码功能。

![](https://github.com/Banjo-Acane/HospitalInformationSystem/blob/main/images/1.jpg)



2. ###### 挂号界面

   自动填写发票号与病历号（允许手动更改）

   *标注的为必填项，若未填满必填信息就点击挂号按钮，则会弹出提示窗口，无法进行挂号操作。

   
![](https://github.com/Banjo-Acane/HospitalInformationSystem/blob/main/images/2.png)

   

3. ###### 医院分诊界面

   左侧显示所有挂号后患者，选择加入待诊列表后患者移入待诊队列。

   可为患者添加权重值。

![](https://github.com/Banjo-Acane/HospitalInformationSystem/blob/main/images/3.png)

   

4. ###### 病种树查询与患者排序
![](https://github.com/Banjo-Acane/HospitalInformationSystem/blob/main/images/4.png)
 
##### 算法设计：

​	冒泡排序、快速排序、最大堆；顺序查找、二分查找；树的广度优先遍历。

###### 	患者优先度

​		患者分为A（初诊患者）和B（复诊患者）两种类型，复诊患者适当地提高优先度，穿	插在初诊患者队伍中间。链表实现。


