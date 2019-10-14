package base

import java.util

import org.apache.spark.{SparkConf, SparkContext}

/**
  *  wordcount 例子
  */
object WordCount {
  def main(args: Array[String]): Unit = {
    //读取resouce路径
    val resource = WordCount.getClass.getResource("/")

    val conf = new SparkConf().setAppName("wordCount").setMaster("local")
    val spark = new SparkContext(conf)

    val rd = spark.textFile(resource+"wordCount")
    rd.flatMap(w => w.split(" ")).map(w => (w, 1)).reduceByKey(_ + _).foreach(w => println(s"word:${w._1} count:${w._2}"))
  }
}
