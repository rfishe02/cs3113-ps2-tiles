
#"C:/Users/fishe/eclipse-workspace/CS_3113_PS2/"

manhatt <- read.csv("C:/Users/fishe/eclipse-workspace/CS_3113_PS2/manhatt_results.txt",header=TRUE)
disp <- read.csv("C:/Users/fishe/eclipse-workspace/CS_3113_PS2/disp_results.txt",header=TRUE)

# Take a subset of the found solutions.

manhatt_found <- subset(manhatt,found=='1')
disp_found <- subset(disp,found=='1')

# Find the percentage of solutions found.

manhatt_per_found <- length(manhatt_found[,1])/length(manhatt[,1])
disp_per_found <- length(disp_found[,1])/length(disp[,1])

# Get the five number summary for the steps.

summary(manhatt_found$steps)
summary(disp_found$steps)

# Create a density plot for the number of steps.

den_mht <- density(manhatt_found$steps)
den_dsp <- density(disp_found$steps)

plot(den_mht,col=rgb(0,0.10,1,0.50),main="The Density of The Number of Solution Steps in each Hueristic",xlab="Number of Steps in a Solution")
polygon(den_mht, col=rgb(0,0.10,1,0.50), border=rgb(0,0.10,1,0.50))
polygon(den_dsp, col=rgb(1,0.10,0,0.50), border=rgb(1,0.10,0,0.50))

legend("topleft", inset=.05, title="Heuristic",
       c("Manhattan","Displaced"), fill=c(rgb(0,0.10,1,0.50),rgb(1,0.10,0,0.50)), horiz=FALSE)

# Create a box plot for the number of steps.



