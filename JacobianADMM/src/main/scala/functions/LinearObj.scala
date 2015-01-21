package functions

import breeze.linalg.{DenseVector => BDV,DenseMatrix => BDM}

class LinearObj(val c: BDV[Double]) extends Function1[BDV[Double], Double] with Update with Serializable{
  def apply(x:BDV[Double]) : Double = {
    c dot x
  }
  
 def Update (A: BDM[Double], z: BDV[Double], x_old: BDV[Double], rho: Double): BDV[Double] = {
   val dimension: Int = A.cols 
   
   val I = BDM.eye[Double](dimension) * 0.1
   
   val a: BDM[Double] = A.t * A + I
   
   val b: BDV[Double] = A.t * A * x_old + I * x_old - A.t * z - c
   
   a\b
 }
}