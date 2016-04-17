olsgwr <- function(formuladata,datas,kernels,DM){
    bw1 <- bw.gwr(formula = formuladata, data=datas, kernel = kernels,dMat=DM)
    gwr.res1 <- gwr.basic(formula = formuladata, data=datas, bw=bw1,kernel = kernels,dMat=DM)
    return(gwr.res1)
}