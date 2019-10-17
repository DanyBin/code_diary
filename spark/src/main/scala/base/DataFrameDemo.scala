package base

import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 用于DataFrame 的demo
  */
object DataFrameDemo {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("DataFrameDemo").setMaster("local")
    val session = SparkSession.builder().config(conf).getOrCreate()

    import  session.implicits._
    //创建DataFrame == 表
    val aboesDF = List(Abo(101,("w","AG")),Abo(102,("s","AG")),Abo(103,("t","AG")),Abo(104,("a","AG")),Abo(105,("b","AG"))).toDF()
    val locsDF = List(Loc(101,"Bern"),Loc(101,"Thun"),Loc(102,"Lausanne"),Loc(103,"Geneve")).toDF()


    aboesDF.show()
    locsDF.show()

    //同时有订阅且有地点信息的顾客. 内链接
    aboesDF.join(locsDF,aboesDF("id") === locsDF("id")).show()

    //获取订阅了但是没有地点信息的顾客。 左连接
    aboesDF.join(locsDF,aboesDF("id") === locsDF("id"),"left_outer").show()
  }
}
