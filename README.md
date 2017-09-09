# DbToBeanClassUtil
读取数据库中的表创建相应的Bean类工具。<br>
支持数据库中命名与驼峰式命名转换。<br>
conf.xml中对数据库,数据库中表的名配置,则能读取数据库中相应的表，并且能在配置的包下生成对应的Bean类,默认包路径为com.test.beans,可以在Constants进行改变。可以对每一列的名字进行配置，也可以配置bean类的父类和其实现的接口。<br>
