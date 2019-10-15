package base

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 验证shuffles对应性能影响
  *
  * 例子: 计算有多少趟行程，以及每个顾客的花费
  */

object ShuffleDemo {

  def main(args: Array[String]): Unit = {
    //数据
    var purchase = List(CFFPurchase(100, "jiao", 22.25),
      CFFPurchase(200, "wang", 42.12),
      CFFPurchase(300, "li", 44.25),
      CFFPurchase(100, "le", 100.25),
      CFFPurchase(200, "lin", 10.25),
      CFFPurchase(100, "zhang", 22.25),
      CFFPurchase(300, "shan", 120.25),
      CFFPurchase(100, "jia", 22.25),
      CFFPurchase(200, "ma", 22.25))

    val conf = new SparkConf().setAppName("shuffeDemo").setMaster("local")
    val sc = new SparkContext(conf)

    val purRDD = sc.parallelize(purchase)

    val s1 = System.currentTimeMillis()
    val result = purRDD.map(m => (m.customerId, m.price))
      .groupByKey()
      .map(p => (p._1, (p._2.size, p._2.sum)))
      .collect()

    println(s"方案1,总耗时:${(System.currentTimeMillis() - s1)}")
    result.foreach(p => println(s"ID=${p._1} 次数${p._2._1} 总花费${p._2._2}"))


    /**
      * 优化思路： 没有必要将所有的键值对都通过网络传输，可以在shuffle之前先reduce，这样能极大地减少网络传输的数据
      * reduceByKey(func: (V, V) => V): RDD[(K, V)]，从概念上来说，reduceByKey相当于先做groupByKey再reduce，但是效率要高得多
      * 过程: 先节点内部reduce，shuffle之后再reduce
      */
    val s2 = System.currentTimeMillis()
    val resultTuple = purRDD.map(m => (m.customerId, (1, m.price)))
      .reduceByKey((v1, v2) => (v1._1 + v2._1, v1._2 + v2._2))
      .collect()

    println(s"方案2,总耗时:${(System.currentTimeMillis() - s2)}")
    resultTuple.foreach(p => println(s"ID=${p._1} 次数${p._2._1} 总花费${p._2._2}"))
  }

}

case class CFFPurchase(customerId: Int, name: String, price: Double)