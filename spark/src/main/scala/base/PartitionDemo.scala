package base

import org.apache.spark.rdd.RDD
import org.apache.spark.{RangePartitioner, SparkConf, SparkContext}

/**
  *  验证分区(partitons的使用)
  */

object PartitionDemo {

  def main(args: Array[String]): Unit = {
    //数据
    var purchase = List(CFFPurchase(100, "jiao", 22.25),
      CFFPurchase(200, "wang", 42.12),
      CFFPurchase(300, "li", 44.25),
      CFFPurchase(400, "le", 100.25),
      CFFPurchase(500, "lin", 10.25),
      CFFPurchase(600, "zhang", 22.25),
      CFFPurchase(900, "shan", 120.25),
      CFFPurchase(1100, "jia", 22.25),
      CFFPurchase(2200, "ma", 22.25))

    val conf = new SparkConf().setAppName("Partitioner").setMaster("local")
    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(purchase)

    val s = System.currentTimeMillis()
    val pair = rdd.map(p => (p.customerId,(1,p.price)))
    //显示调用分区函数&缓存
    val rangePair = new RangePartitioner(8,pair)
    val partitioned = pair.partitionBy(rangePair).persist()

    val resultTuple = partitioned.map(p =>p).reduceByKey((v1,v2) => (v1._1 + v2._1,v1._2+v2._2)).toDebugString

    println(s"分区方案,总耗时:${(System.currentTimeMillis() - s)}")
    println(resultTuple)

  }
}
