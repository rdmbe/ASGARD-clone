dMat <- function(xcoordinate,ycoordinate){
    coordinate <- cbind(xcoordinate,ycoordinate)
    DM <- gw.dist(dp.locat=coordinate)
    return(DM)
}