package base

case class CFFPurchase(customerId: Int, name: String, price: Double)

/**
  * 订阅信息
  * @param id
  * @param v
  */
case class Abo(id:Int,v:(String,String))

/**
  * 地点信息
  * @param id
  * @param v
  */
case class Loc(id:Int,v:String)
