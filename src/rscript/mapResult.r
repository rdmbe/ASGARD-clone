if(!is.element("grDevices", installed.packages()[,1]))
{
  install.packages("grDevices")
}
library(grDevices)

data <- as.data.frame(data)
data <- as.matrix(data)
dimensi <- dim(data)
value <- matrix(0, nrow=dimensi[1], byrow=T)
dataplot <- data[,var]
dataplot <- data.frame(lapply(dataplot, as.character), stringsAsFactors=FALSE)
for(i in 1:dimensi[1]){
  value[i] <- as.numeric(sub(",", ".", dataplot[i], fixed = TRUE))
}
pop.df <- data.frame(id=id, value, centroid.reg.f)

Map1 <- ggplot(pop.df, aes(map_id=id)) 
Map1 <- Map1 + geom_map(aes(fill=value), map = reg.f, colour="grey23") 
#guides(fill=guide_legend(title=var)) 
Map1 <- Map1 + expand_limits(x = reg.f$long, y = reg.f$lat)
Map1 <- Map1 + coord_equal()
Map1 <- Map1 + geom_text(size=3, aes(label=id, x=Longitude, y=Latitude), color="white")
Map1 <- Map1 + labs(x = "Longitude", y = "Latitude", title = "") + theme_bw()
png(pathdir, height=500, width=1000, res=100)
print(Map1)
dev.off()