
#"C:/Users/fishe/eclipse-workspace/CS_3113_PS2/"

first <- read.csv("C:/Users/fishe/eclipse-workspace/CS_3113_PS2/first_results.txt",header=TRUE)
second <- read.csv("C:/Users/fishe/eclipse-workspace/CS_3113_PS2/second_results.txt",header=TRUE)

first_found <- subset(first,found=='1')
second_found <- subset(second,found=='1')

first_per_found <- length(first_found[,1])/length(first[,1])
second_per_found <- length(second_found[,1])/length(second[,1])

summary(first_found$steps)
summary(second_found$steps)


