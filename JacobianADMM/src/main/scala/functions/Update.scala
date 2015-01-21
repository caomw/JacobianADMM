package functions

import breeze.linalg._
import breeze.linalg.{DenseVector => BDV, DenseMatrix => BDM}



trait Update {
  def Update (A: BDM[Double],
               z: BDV[Double],
               x_old: BDV[Double],
               rho: Double): BDV[Double]
  
}
