bricks = [ ]

with open('input.txt','r') as f:
    for ligne in f.read().splitlines():
        gauche, droite = ligne.split('~')
        xd, yd ,zd  = (int(s) for s in gauche.split(','))
        xf, yf, zf = (int(s) for s in droite.split(','))
        bricks.append(  ((xd,yd,zd), (xf, yf, zf))      )


bricks = sorted(bricks, key = lambda brick : brick[0][2])

occupied = set()

def tiles(brick):
    (xd,yd,zd), (xf, yf, zf) = brick
    if xd < xf: return [ (x,yd,zd) for x in range(xd, xf+1)  ]
    if yd < yf: return [ (xd,y,zd) for y in range(yd, yf+1)  ]
    if zd < zf: return [ (xd,yd,z) for z in range(zd, zf+1)  ]
    return [(xd,yd,zd)]

def isvalid(brick):
    return all(   (x,y,z) not in occupied for (x,y,z) in tiles(brick)  )


newbricks = [ ]
for brick in bricks:
    (xd,yd,zd), (xf, yf, zf) = brick

    while isvalid( ((xd,yd,zd-1), (xf, yf, zf-1))  ) and zd-1 >= 1 :
        zd -=  1
        zf -= 1

    newbricks.append(  ((xd,yd,zd), (xf, yf, zf))      )

    for (x,y,z) in tiles(((xd,yd,zd), (xf, yf, zf)) ):
        occupied.add((x,y,z))


newbricks = sorted(newbricks, key = lambda brick : brick[0][2])
N = len(newbricks)

def supports(brick1, brick2):
    for tile in tiles(brick1):
        x,y,z = tile
        if (x,y,z+1) in tiles(brick2):
            return True
    return False

parents = { i:[] for i in range(N) }
children = { i:[] for i in range(N) }
for i, brick1 in enumerate(newbricks):
    for j, brick2 in enumerate(newbricks):

        if brick2[0][2] > brick1[1][2] + 1:  # on profite du tri
            break

        if j>i and supports(brick1, brick2):
            parents[j].append(i)
            children[i].append(j)


# j in disintegration[i] means i ALONE supports j
disintegrationgraph = {i:[] for i in range(N)}
for i in range(N):
    for j in range(N):
        if parents[j] == [i]:
            disintegrationgraph[i].append(j)

print('Part 1 :', sum(disintegrationgraph[i] == [ ] for i in range(N)))


## Part 2
''' Bricks falling when deleting brick B are bricks not visited by a
traversal that ignores B '''

total = 0
for K in range(N):
    vus = {i for i in range(N) if newbricks[i][0][2] == 1}
    pile = [i for i in range(N) if newbricks[i][0][2] == 1 and i!=K]

    while len(pile) > 0:
        x = pile.pop()
        if x!=K:
            for v in children[x]:
                if v not in vus:
                    pile.append(v)
                    vus.add(v)

    total += N-len(vus)

print('Part 2 :', total)
