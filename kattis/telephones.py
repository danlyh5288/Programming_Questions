import sys

while True:

    # Store the number of calls and intervals
    line = sys.stdin.readline().rstrip()
    numCalls = int((line.split())[0])
    numIntervals = int((line.split()[1]))

    # EOF
    if numCalls == numIntervals == 0:
        break

    # two lists stores calls interval and police interval
    tuples = []
    intervals = []
    for i in range(numCalls):
        line = sys.stdin.readline().rstrip()
        first = int((line.split())[2])
        second = int((line.split())[3])
        tuples.append((first, first+second))

    for i in range(numIntervals):
        line = sys.stdin.readline().rstrip()
        first = int((line.split())[0])
        second = int((line.split())[1])
        intervals.append((first, first + second))

    # for each police interval, check all the call intervals
    for interval in intervals:
        count = 0
        for tup in tuples:
            if interval[1] > tup[0] and interval[0] < tup[1]:
                count += 1

        print(count)
