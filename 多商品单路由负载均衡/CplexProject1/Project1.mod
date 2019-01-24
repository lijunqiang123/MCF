/*********************************************
 * OPL 12.6.3.0 Model
 * Author: lJQ
 * Creation Date: 2018-10-28 at ÉÏÎç1:17:32
 *********************************************/

int linksNum = ...;
int nodesNum = ...;
int backPathNum = ...;
int sdNum = nodesNum*(nodesNum-1);

range Links = 1..linksNum;
range BackPath = 1..backPathNum;
range SD = 1..sdNum;

int capacity[Links] = ...;
int demand[SD] = ...;
int delta[SD][Links][BackPath] = ...;


dvar float+ z;
dvar boolean x[SD][BackPath];

minimize z;

subject to {
  forall( k in SD )
    ctDemand:
      sum( p in BackPath )
        x[k][p] * demand[k] == demand[k];

  forall( l in Links )
    ctUtilizeRate:
      sum( k in SD )
        sum ( p in BackPath )
          delta[k][l][p] * x[k][p] * demand[k] <= z * capacity[l];
} 
 