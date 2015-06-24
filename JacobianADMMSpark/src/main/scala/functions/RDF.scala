package functions

import org.apache.spark.rdd.RDD
import org.apache.spark.Logging
import breeze.linalg.{DenseVector => BDV, DenseMatrix => BDM}

class RDF[F <: Function1[BDV[Double], Double] with Update](val splits:RDD[F], var nSplits:Long) 
  extends Function1[BDV[Double], Double] with Serializable with Logging{
  
  def Update(z: BDV[Double], x_old: RDD[BDV[Double]], A:RDD[BDM[Double]], rho: Double): RDD[BDV[Double]] = {
 
    splits.zip(x_old).zip(A).map({case ((fn, x_i), _3) => fn.Update(z, x_i, _3, rho)})
  }
    
  def numDeps() : Int = {
    numDeps(splits)
  }
  
  def numDeps(rdd: RDD[_]): Int = {
    var nDeps = 0
    for (dep <- rdd.dependencies) {
      nDeps += 1
      nDeps += numDeps(dep.rdd)
    }
    nDeps
  }
  
  def numSplits(): Long = {
    if (nSplits == 0L) {
      nSplits = splits.count
    }
    nSplits
  }
  
  def apply(x: BDV[Double]):Double = {
    println("Dummy function! Use dApply instead! ")
    0.0
  }
  
  def dApply(x: RDD[BDV[Double]]): Double = {
    //should divide according to splits, for simple, assume equal length.
    //
    splits.zip(x).map({case (fn, x_i) => fn(x_i)}).reduce(_+_)
  }

}