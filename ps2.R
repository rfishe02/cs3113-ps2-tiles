
#"C:/Users/fishe/eclipse-workspace/CS_3113_PS2/"

manhatt <- read.csv("C:/Users/fishe/eclipse-workspace/CS_3113_PS2/manhatt_results.txt",header=TRUE)
disp <- read.csv("C:/Users/fishe/eclipse-workspace/CS_3113_PS2/disp_results.txt",header=TRUE)

# Take a subset of the found solutions.

manhatt_not <- subset(manhatt,found=='0')
disp_not <- subset(disp,found=='0')

manhatt_found <- subset(manhatt,found=='1')
disp_found <- subset(disp,found=='1')

# Find the percentage of solutions found.

length(manhatt_found[,1])/length(manhatt[,1])
length(disp_found[,1])/length(disp[,1])

# Get the five number summary for the steps.

summary(manhatt_found$steps)
summary(disp_found$steps)

# Create a density plot for the number of steps.

den_mht <- density(manhatt_found$steps)
den_dsp <- density(disp_found$steps)

plot(den_dsp,col=rgb(1,0.10,0,0.50),main="The Density of The Number of Solution Steps in each Hueristic",xlab="Number of Steps in a Solution")
polygon(den_dsp, col=rgb(1,0.10,0,0.50), border=rgb(1,0.10,0,0.50))
polygon(den_mht, col=rgb(0,0.10,1,0.50), border=rgb(0,0.10,1,0.50))

legend("topright", inset=.05, title="Heuristic",
       c("Displaced","Manhattan"), fill=c(rgb(1,0.10,0,0.50),rgb(0,0.10,1,0.50)), horiz=FALSE)

# Get the five number summary for the number of milliseconds.

summary(manhatt_found$time_millis)
summary(disp_found$time_millis)

summary(manhatt_not$time_millis)
summary(disp_not$time_millis)

# Create a density plot for the number of milliseconds.

den_mht_tim <- density(manhatt$time_millis)
den_dsp_tim <- density(disp$time_millis)

plot(den_dsp_tim,col=rgb(1,0.10,0,0.50),main="The Density of The Search Time Length in each Hueristic",xlab="Time (Milliseconds)")
polygon(den_dsp_tim, col=rgb(1,0.10,0,0.50), border=rgb(1,0.10,0,0.50))
polygon(den_mht_tim, col=rgb(0,0.10,1,0.50), border=rgb(0,0.10,1,0.50))

legend("topright", inset=.05, title="Heuristic",
       c("Displaced","Manhattan"), fill=c(rgb(1,0.10,0,0.50),rgb(0,0.10,1,0.50)), horiz=FALSE)

# Create a composite frame.

frame <- data.frame(
  heuristic = c(rep("manhatt",length(manhatt[,1])),rep("disp",length(disp[,1]))), 
  found = c(manhatt$found,disp$found),
  time = c(manhatt$time_millis,disp$time_millis),
  steps = c(manhatt$steps,disp$steps)
  )

# Check to see whether some data is normally distributed.

#H0: The data is normally distributed.
shapiro.test(manhatt_found$steps)[[2]]<=0.05
shapiro.test(disp_found$steps)[[2]]<=0.05

# Create box plots.

boxplot(frame$steps ~ frame$heuristic,horizontal=TRUE,
        names=c("Displaced","Manhattan"),
        ylab="Heuristic",xlab = "Number of Steps",
        main="The Distribution of The Number of Solution Steps in each Hueristic",
        col=c(rgb(1,0.10,0,0.50),rgb(0,0.10,1,0.50)))

frame_subset <- subset(anova_frame,found=='1')
frame_no_subset <- subset(anova_frame,found=='0')

boxplot(frame_subset$time ~ frame_subset$heuristic,horizontal=TRUE,
        names=c("Displaced","Manhattan"),
        ylab="Heuristic",xlab = "Time (Milliseconds)",
        main="The Distribution of a Successful Search Length in each Hueristic",
        col=c(rgb(1,0.10,0,0.50),rgb(0,0.10,1,0.50)))

boxplot(frame_no_subset$time ~ frame_no_subset$heuristic,horizontal=TRUE,
        names=c("Displaced","Manhattan"),
        ylab="Heuristic",xlab = "Time (Milliseconds)",
        main="The Distribution of a Unsuccessful Search Length in each Hueristic",
        col=c(rgb(1,0.10,0,0.50),rgb(0,0.10,1,0.50)))

