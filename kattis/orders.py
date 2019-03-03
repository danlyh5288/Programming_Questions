import sys

# number of items in the menu
items = int(input())

# cost of each item in the menu
costs = sys.stdin.readline().rstrip()
item_cost = []*items
[item_cost.append(int(cost)) for cost in costs.split()]

# store the common multiples of the orders cost in a list
multiples = [-2] * 32000
multiples[0] = 0
for i, cost in enumerate(item_cost):
    for j in range(30001):
        if multiples[j] >= 0:
            if multiples[j+cost] == -2:
                multiples[j+cost] = i
            else:
                multiples[j+cost] = -1
        if multiples[j] == -1:
            multiples[j+cost] = -1

# number of orders
orders = int(input())

# total cost of different orders
ttl = sys.stdin.readline().strip()
total_costs = []*orders
[total_costs.append(total_cost) for total_cost in ttl.split()]

# traverse all the cases
for i in range(orders):
    total = int(total_costs[i])
    result = []
    if multiples[total] == -2:
        print("Impossible")
        continue
    if multiples[total] == -1:
        print("Ambiguous")
        continue
    while total > 0:
        result.append(multiples[total]+1)
        total -= item_cost[multiples[total]]
    if total < 0:
        print("Ambiguous")
        continue
    result.sort()
    res = ""
    for val in result:
        res += str(val) + " "
    print(res)
