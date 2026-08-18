try:
    print('\n'.join
          ([''.join
            ([('Happy Women\'s day Bridgit '[(x - y) % 26]
               if ((x * 0.05) ** 2 + (y * 0.1) ** 2 - 1)
                  ** 3 - (x * 0.05) ** 2 * (y * 0.1)
                  ** 3 <= 0 else ' ')
              for x in range(-30, 30)])
            for y in range(15, -15, -1)]))
    print("Happy Women's Day Bbg!!")
except IndexError:
    print("Index out of bound")
