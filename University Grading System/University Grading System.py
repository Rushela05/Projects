# I declare that my work contains no example of misconduct, such as plagiarism, or collusion
# Any code taken from other sources is referenced within my code solution

#Student ID: 20220809
#Date: 28/11/2023

#Importing Graphics.py
from graphics import GraphWin, Rectangle, Point, Text 

#Initializing Variables

Pass = 0
Defer = 0
Fail = 0
total_credits = 0
credit=0
output=0
Continue = 'y'
results= [ ]


#Define a function to calculate the progression outcome
        
def progress_output(Pass, Defer, Fail):

    #Calculating the progression outcome
    total_credits = Pass + Defer + Fail

    #If the total is not 120
    if total_credits > 120 or total_credits < 120:
        return "Incorrect total"

    #Checking the conditions for each outcome
    elif total_credits == 120 and Pass == 120:
        return "Progress"

    elif Pass == 100 and (Defer == 20 or Defer == 0) and (Fail == 20 or Fail == 0):
        return "Progress (Module Trailer)"

    elif Pass in [80, 60, 40, 20, 0] and Defer in [100, 80, 60, 40, 20, 0] and Fail in [0, 20, 40, 60,]:
        return "Module Retriever"
    
    
    elif Fail == 80 or Fail == 100:
        return "Exclude"
    else:
        print()

#Function to save the progression data in a text file
def save_progress_data(data):
    with open("prgress_data.txt","a")as file:
            file.write(",".join(map(str,data)) + "\n")

#Function to read the progression data from the text file
def read_progress_data():
    data = []
    try:
        with open("progress_data.txt","r") as file:
            for line in file:
                entry = list(map(int, line.strip().split(",")))
                data.append(entry)
    except FileNotFoundError:
        pass
    return data

#User inputs
while True:
    try:
        Pass=int(input("Please enter your credits at pass: "))
        Defer=int(input("Please enter your credits at defer: "))
        Fail=int(input("Please enter your credits at fail: "))

        #To check if the inputs are in range of 0 - 120
        if Pass%20 != 0:
            print("Out of range")
        elif Defer%20 != 0:
            print("Out of range")
        elif Fail%20 != 0:
            print("Out of range")
        else:
            print()

        #Calculating and printing the progression output
        output = progress_output(Pass, Defer, Fail)         #Use of the function
        
        #Add the set of inputs and output to the list
        results.append([Pass, Defer, Fail, output])

        #Save progression data to a text file
        save_progress_data([Pass, Defer, Fail, output])

        print(output)
        print()

        print("Would you like to enter another set of data?")

        Continue=str(input("Enter 'y' for yes or 'q' to quit and view results: "))

        if Continue == 'y':
            print()
        elif Continue == 'q':
            break
        else:
            print("Invalid Input")
            print() 
            print("Would you like to enter another set of data?")  #Giving the user another chance to input a data
            Continue=str(input("Enter 'y' for yes or 'q' to quit and view results: "))
            print()
            if Continue == 'y':
                print()
            elif Continue == 'q':
                break
            elif Continue != 'y' and Continue != 'q':
                print()
                print(f"{result[3]} - {result[0]}, {result[1]}, {result[2]}")
                break

    #Value Error
    except ValueError:
        print("Integer Required")
        print("")

#Initialize variable for Histogram
progress_count = 0
trailer_count = 0
retriever_count = 0
exclude_count = 0

#calculating counts for each category
for result in results:
    if result[3] == "Progress":
        progress_count += 1
    elif result[3] == "Progress (Module Trailer)":
        trailer_count += 1
    elif result[3] == "Module Retriever":
        retriever_count += 1
    elif result[3] == "Exclude":
        exclude_count += 1

#Dsiplay the Histogram
win=GraphWin("Histogram Results", 500,350)
win.setBackground("white")

#Heading
heading=Text(Point(200,20), "Histogram Results")
heading.setSize(14)
heading.setStyle("bold")
heading.draw(win)

#Define category names
categories = ["Progress", "Trailer", "Retriever", "Exclude"]
category_counts = [progress_count, trailer_count, retriever_count, exclude_count]
colors = ["lightgreen", "lightblue", "orange", "pink"]


#Drawing rectangles
for i in range(len(categories)):
    height = category_counts[i]*30
    aRect = Rectangle(Point(50+i*100, 250), Point(100+i*100,250 - height))
    aRect.setFill(colors[i])
    aRect.draw(win)

    #Display labels and counts
    label = Text(Point(75+i*100,270), categories[i])
    label.draw(win)
    count_label = Text(Point(75+i*100,250-height/2), str(category_counts[i]))
    count_label.draw(win)

#Total count
total_students = sum(category_counts)
total = Text(Point(200, 300), f"{total_students} outcomes in total ")
total.setSize(14)
total.setStyle("bold")
total.draw(win)

win.getMouse()
win.close()

#Display results
print("Part 2")
print()
for result in results:
    print(f"{result[3]} - {result[0]}, {result[1]}, {result[2]}")

#Display the results from the text file
print("")
print("Part 3")
print()
stored_data = read_progress_data()
for entry in stored_data:
    # Assuming the correct order is [Pass, Defer, Fail, output]
    print(f"{result[3]} - {result[0]}, {result[1]}, {result[2]}")




