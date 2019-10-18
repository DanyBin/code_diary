package base

import org.apache.spark.sql.expressions.Aggregator
import org.apache.spark.sql._

/**
  * 用于DataSet Demo
  */
object DataSetDemo {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("DatasetDemo").master("local").getOrCreate()

    val locs = List(Loc(101, "Bern"),Loc(101, "Bern"), Loc(101, "Thun"), Loc(102, "Lausanne"), Loc(103, "Geneve"))


    /*********************** 「Dataset 的创建操作」**************************************************/

    import spark.implicits._
    //scala类型创建
    val ds = locs.toDS()

    //通过 DataFrame创建
    val df =locs.toDF()
    val encoder = org.apache.spark.sql.Encoders.product[Loc]
    val dfToDs = df.as(encoder)

    //通过RDD创建
    val rddToDs = spark.sparkContext.parallelize(locs).toDS().persist()

    //直接读取文件创建
//   val fileToDs = spark.read.json("xxx.json").as[Loc]


    /**
      * Dataset上的Transformation变换操作 - 「弱类型」
      * 与DataFrame操作一样
      */
    rddToDs.select($"v").where($"id" === 101).show()

    /**
      * Dataset上的Transformation变换操作 - 「强类型」
      *
      */
     val list = List((3,"a"),(4,"b"),(5,"c"),(6,"d"))
     list.toDS().map(p => (p._1 + 1))



   /*********************** 「Dataset 的聚合操作」**************************************************/

    /**
      * Dataset 的聚合操作,需要groupByKey,如果采用groupby时，返回dataFrame
      */
    val datasetTuple: Dataset[(String, Long)] = rddToDs.groupByKey(_.v).count()

    val dataFrameTuple: DataFrame = rddToDs.groupBy($"v").count()


    rddToDs.groupByKey(_.v).reduceGroups((v1,v2)=>Loc(v1.id+v2.id,v1.v)).show()
    /**
      * +--------+--------------------------+
        |   value|ReduceAggregator(base.Loc)|
        +--------+--------------------------+
        |  Geneve|             [103, Geneve]|
        |    Bern|               [202, Bern]|
        |    Thun|               [101, Thun]|
        |Lausanne|           [102, Lausanne]|
        +--------+--------------------------+
      */


    import org.apache.spark.sql.functions._
    rddToDs.groupByKey(_.v).agg(sum($"id").as[Int]).show()
    /**
      * +--------+-------+
        |   value|sum(id)|
        +--------+-------+
        |  Geneve|    103|
        |    Bern|    202|
        |    Thun|    101|
        |Lausanne|    102|
        +--------+-------+
      */


    rddToDs.groupByKey(_.v)
          .mapGroups((n ,v ) =>{
            (n,v.map(l => l.id).reduceLeft(_+_))
          }).show()
    /**
      * +--------+---+
        |      _1| _2|
        +--------+---+
        |  Geneve|103|
        |    Bern|202|
        |    Thun|101|
        |Lausanne|102|
        +--------+---+
      */



    rddToDs.groupByKey(_.v)
      .flatMapGroups((v,s)=> {
        List((v,s.map(l => l.id).reduceLeft(_ + _)))
      }).show()
    /*
    +--------+---+
    |      _1| _2|
    +--------+---+
    |  Geneve|103|
    |    Bern|202|
    |    Thun|101|
    |Lausanne|102|
    +--------+---+
      */


    /**
      * Dataset上如何实现reduceByKey
      */

    rddToDs.groupByKey(_.v).mapValues(_.id).reduceGroups(_+_).show()
    /**
      * +--------+---------------------+
        *|   value|ReduceAggregator(int)|
        *+--------+---------------------+
        *|  Geneve|                  103|
        *|    Bern|                  202|
        *|    Thun|                  101|
        *|Lausanne|                  102|
        *+--------+---------------------+
      */



    /***************************「Dataset中的 Aggregator方法使用」**********************************************/

    val aggFunValue = List((1, "a"), (2, "b"), (2, "c"), (4, "d"), (5, "f"))

    val aggDS = aggFunValue.toDS()

    val strConcat: TypedColumn[(Int, String), String] = new Aggregator[(Int, String), String, String] {
      override def zero: String = ""

      override def reduce(b: String, a: (Int, String)): String = b + a._2

      override def merge(b1: String, b2: String): String = b1 + b2

      override def finish(reduction: String): String = reduction

      override def bufferEncoder: Encoder[String] = Encoders.STRING

      override def outputEncoder: Encoder[String] = Encoders.STRING
    }.toColumn


    aggDS.groupByKey(_._1).agg(strConcat.as[String]).show()

    /**
      * +-----+--------------------+
        |value|anon$1(scala.Tuple2)|
        +-----+--------------------+
        |    1|                   a|
        |    5|                   f|
        |    4|                   d|
        |    2|                  bc|
        +-----+--------------------+
      */



  }

}