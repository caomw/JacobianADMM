package com.lanchao.JacobianADMMSpark

import functions._
import breeze.linalg.{DenseVector => BDV, DenseMatrix => BDM}
import breeze.linalg._
import breeze.numerics._
import breeze.linalg.operators._
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.broadcast._

object Main {
  
  case class Params (
      numblocks: Int = 3,
      blockheight: Int = 500,
      maxiters: Int = 300,
      rho: Double = 1.0
  )
  
  val params = Params();
  
  def main(args:Array[String]) {
    run(params)
  }
  
  def run(params:Params) {
    val conf = new SparkConf()
                   .setAppName("Test Linear")
                   .setMaster("local[4]")
                   
    val sc = new SparkContext(conf)

// It seems that in breeze. The matrix and vector are "private" and are not seq[] type. Need check.
    
  val c = sc.parallelize(1 to params.numblocks, params.numblocks).map( i => BDV.ones[Double](4))
  var x = sc.parallelize(1 to params.numblocks, params.numblocks).map( i => BDV.ones[Double](4))
  val A = sc.parallelize(1 to params.numblocks, params.numblocks).map( i => BDM.eye[Double](4))
//  val b = sc.parallelize(1 to params.numblocks, params.numblocks).map( i => (10,20,30,40).asInstanceOf[BDV[Double]])
  
  val b : BDV[Double] = BDV[Double](10.0,20.0,30.0,40.0)
  
  var z: BDV[Double] = BDV.zeros[Double](4)
  var zb: Broadcast[BDV[Double]] = sc.broadcast(z); 
  
  val rho: Double = 0.02  
  var lambda: BDV[Double] = BDV.zeros[Double](4)
  var done = 1
  
  val f = LinearObj.fromLocal(c)
  f.splits.cache()
  
  while (done < 40) {
    z = A.zip(x).map(Ax => Ax._1 * Ax._2).reduce(_+_) - b - lambda
    zb = sc.broadcast(z)
    x = f.Update(zb.value, x, A, rho)
    lambda = lambda - (A.zip(x).map(Ax => Ax._1 * Ax._2).reduce(_+_) - b) * 0.9
    done += 1
  }
  
  
  
  
  println("success!!!!!")
  
  x.collect().foreach(println) 
  println("success!!!!!" + f.dApply(x)) 
     
  
  
   
    
//    val A = sc.parallelize(1 to params.numblocks,params.numblocks).map(x => BDM.rand(params.blockheight,500))
//    val c = sc.parallelize(1 to params.numblocks, params.numblocks).map(x => BDV.rand(params.blockheight))
//    var c = sc.parallelize(1 to params.numblocks, params.numblocks).map(i => Array(1,2,3,4,5))
      
//      
    
 //     val s = c.reduce((a,b) => if (a(1) == b(1)) a else b)
        
//    val data = BDV.ones[Double](5)
//    val c = sc.parallelize(data)
//     var s = c.count()
      
   
    
    
    sc.stop
       
  }
  
    
    
 
/*    
    var x_1: BDV[Double] = BDV.ones[Double](2)
    var x_2: BDV[Double] = BDV.ones[Double](2)
    var x_3: BDV[Double] = BDV.ones[Double](2)
    
    val c_1: BDV[Double] = BDV[Double](1.0, 2.0)
    val c_2: BDV[Double] = BDV[Double](3.0, 4.0)
    val c_3: BDV[Double] = BDV[Double](4.0, 5.0)
    
    val A_1 = BDM((1.0,0.0),(0.0,1.0),(0.0,0.0),(0.0,0.0),(0.0,0.0),(0.0,0.0))
    val A_2 = BDM((0.0,0.0),(0.0,0.0),(1.0,0.0),(0.0,1.0),(0.0,0.0),(0.0,0.0))
    val A_3 = BDM((0.0,0.0),(0.0,0.0),(0.0,0.0),(0.0,0.0),(1.0,0.0),(0.0,1.0))
    
    val b: BDV[Double] = BDV[Double](10,20,30,40,50,60)
      
    val f_1 = new LinearObj(c_1, A_1)
    val f_2 = new LinearObj(c_2, A_2)
    val f_3 = new LinearObj(c_3, A_3)

    
    val rho: Double = 1.0  
    
    var lambda: BDV[Double] = BDV.zeros[Double](6)
    
    var done = 1
    var z: BDV[Double] = BDV.zeros[Double](6)
    
    while(done < 20) {
      z = A_1 * x_1 + A_2 * x_2 + A_3 * x_3 - b - lambda
      x_1 = f_1.Update(z,x_1,rho)
      x_2 = f_2.Update(z,x_2,rho)
      x_3 = f_3.Update(z,x_3,rho)
      lambda :-= (A_1 * x_1 + A_2 * x_2 + A_3 * x_3 - b) * 0.9
      done += 1
    }
    
    println(x_1,x_2,x_3)
    * 
    */


  
}