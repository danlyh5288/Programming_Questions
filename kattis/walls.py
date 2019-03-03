import math
import sys

# initial inputs
info_input = sys.stdin.readline().rstrip()
infos = info_input.split()

length = float(infos[0])
width = float(infos[1])
num_locations = int(infos[2])
distance = float(infos[3])

# initialize a two dimensional array to store the coordinates of crane locations
locations = []
for i in range(num_locations):
    new = []
    input_location = sys.stdin.readline().rstrip()
    [new.append(float(location)) for location in input_location.split()]
    locations.append(new)

# four midpoints of the wall
left = [-length/2, 0]
right = [length/2, 0]
down = [0, -width/2]
up = [0, width/2]


# check the distance less than or equal to radius of the crane
def check_distance(a, b, r):
    return math.sqrt(math.pow(a[0]-b[0], 2) + math.pow(a[1]-b[1], 2)) <= r


# first dictionary to check if after the loops, all the midpoints are reached
reached = {"left": False, "right": False, "down": False, "up": False}
# second dictionary to check which sides of the wall is reached by each location
location_dict = {}

for i in range(0, num_locations):
    location_dict[i] = []
    if check_distance(left, locations[i], distance):
        reached["left"] = True
        location_dict[i].append("left")
    if check_distance(right, locations[i], distance):
        reached["right"] = True
        location_dict[i].append("right")
    if check_distance(down, locations[i], distance):
        reached["down"] = True
        location_dict[i].append("down")
    if check_distance(up, locations[i], distance):
        reached["up"] = True
        location_dict[i].append("up")

# After checking each crane positions, if there's still a wall that is not reached, then print "impossible"
for k in reached.keys():
    if not reached[k]:
        print("Impossible")
        sys.exit()

# if there's one crane that can reach all the walls
for k in location_dict.keys():
    if len(location_dict[k]) == 4:
        print(1)
        sys.exit()

# if two cranes can reach all the walls
for i in range(num_locations):
    for j in range(i+1, num_locations):
        verify = list(location_dict[i])
        verify.extend(location_dict[j])
        res = set(verify)
        if len(res) == 4:
            print(2)
            sys.exit()

# if three cranes can reach all the walls
for i in range(num_locations):
    for j in range(i+1, num_locations):
        for k in range(j+1, num_locations):
            verify = list(location_dict[i])
            verify.extend(location_dict[j])
            verify.extend(location_dict[k])
            res = set(verify)
            if len(res) == 4:
                print(3)
                sys.exit()

# if four cranes can reach all the walls
print(4)
sys.exit()
