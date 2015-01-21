package com.lanchao.JacobianADMM

import functions._
import breeze.linalg.{DenseVector => BDV, DenseMatrix => BDM}
import breeze.optimize.linear._

object Main {
  def main(args:Array[String]) {
    
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
      
    val f_1 = new LinearObj(c_1)
    val f_2 = new LinearObj(c_2)
    val f_3 = new LinearObj(c_3)

    
    val rho: Double = 1.0  
    
    var lambda: BDV[Double] = BDV.zeros[Double](6)
    
    var done = 1
    var z: BDV[Double] = BDV.zeros[Double](6)
    
    while(done < 20) {
      z = A_1 * x_1 + A_2 * x_2 + A_3 * x_3 - b - lambda
      x_1 = f_1.Update(A_1,z,x_1,rho)
      x_2 = f_2.Update(A_2,z,x_2,rho)
      x_3 = f_3.Update(A_3,z,x_3,rho)
      lambda :-= (A_1 * x_1 + A_2 * x_2 + A_3 * x_3 - b) * 0.9
      done += 1
    }
    
    println(x_1,x_2,x_3)
 }
  
}