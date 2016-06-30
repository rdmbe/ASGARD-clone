if(!is.element("maptools", installed.packages()[,1]))
{
  install.packages("maptools")
}
if(!is.element("rgdal", installed.packages()[,1]))
{
  install.packages("rgdal")
}
if(!is.element("rgeos", installed.packages()[,1]))
{
  install.packages("rgeos")
}
if(!is.element("ggplot2", installed.packages()[,1]))
{
  install.packages("ggplot2")
}
library(maptools)
library(rgdal)
library(rgeos)
library(ggplot2)

index1 <- lapply(strsplit(mapFile, ''), function(mapFile) which(mapFile == '/'))[[1]]
index1 <- index1[length(index1)]
index2 <- lapply(strsplit(mapFile, ''), function(mapFile) which(mapFile == '.'))[[1]]
index2 <- index2[length(index2)]
uploadDirectory <- substr(mapFile, 1, index1-1)
name <- substr(mapFile, index1+1, index2-1)
shpFile <- readOGR(dsn=uploadDirectory, layer=name)
reg <- shpFile

proj4string(reg) <- CRS("+init=epsg:4238")
reg.wgs84 <- spTransform(reg, CRS("+init=epsg:4238"))
reg@data$id <- as.numeric(rownames(reg@data)) + 1
id <- as.numeric(rownames(reg@data)) + 1
reg.f <- fortify(reg, region="id")
centroid.reg.f <- as.data.frame(coordinates(reg))
names(centroid.reg.f) <- c("Longitude", "Latitude")