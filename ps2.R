
#"C:/Users/fishe/eclipse-workspace/CS_3113_PS2/"

manhatt <- read.csv("C:/Users/fishe/eclipse-workspace/CS_3113_PS2/manhatt_results.txt",header=TRUE)
disp <- read.csv("C:/Users/fishe/eclipse-workspace/CS_3113_PS2/disp_results.txt",header=TRUE)

manhatt_found <- subset(manhatt,found=='1')
disp_found <- subset(disp,found=='1')

manhatt_per_found <- length(manhatt_found[,1])/length(first[,1])
disp_per_found <- length(disp_found[,1])/length(second[,1])

summary(manhatt_found$steps)
summary(disp_found$steps)

den_mht <- density(manhatt_found$steps)
den_dsp <- density(disp_found$steps)

plot(den_dsp,col=rgb(0,0.10,1,0.50),main="Density Plot for Solution Steps",xlab="Number of Steps")
polygon(den_dsp, col=rgb(0,0.10,1,0.50), border=rgb(0,0.10,1,0.50))
polygon(den_mht, col=rgb(1,0.10,0,0.50), border=rgb(1,0.10,0,0.50))

legend("topleft", inset=.05, title="Hueristic",
       c("Displaced","Manhattan"), fill=c(rgb(0,0.10,1,0.50),rgb(1,0.10,0,0.50)), horiz=FALSE)


